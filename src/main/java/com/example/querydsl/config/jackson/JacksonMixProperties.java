package com.example.querydsl.config.jackson;

import ch.mfrey.jackson.antpathfilter.AntPathFilterMixin;
import lombok.Data;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author gaochen
 * @date 2019/3/18
 */
@Data
@ConfigurationProperties(prefix = "spring.jackson.mix")
public class JacksonMixProperties {
    private String[] includeClass; //需要包含到antPathFilter的Class列表

    private String[] excludeClass; //需要排除出antPathFilter的Class列表

    private String[] includePackage; //需要包含到antPathFilter的package列表

    private String[] excludePackage; //需要排除出antPathFilter的package列表

    public HashMap<Class<?>, Class<?>> mixMap() {
        HashMap<Class<?>, Class<?>> mixMap = new HashMap<>();
        Set<Class<?>> includeClassSet = getClasses(includePackage); //包含
        Set<Class<?>> excludeClassSet = getClasses(excludePackage); //排除
        List<Class<?>> forceIncludeClassSet = includeClass != null ?
                ReflectionUtils.forNames(Arrays.asList(includeClass), new ClassLoader[]{JacksonMixProperties.class.getClassLoader()}) :
                Collections.emptyList(); //强制包含
        List<Class<?>> forceExcludeClassSet = excludeClass != null ?
                ReflectionUtils.forNames(Arrays.asList(excludeClass), new ClassLoader[]{JacksonMixProperties.class.getClassLoader()}) :
                Collections.emptyList(); //强制排除

        //将强制包含从排除列表删除
        excludeClassSet.removeAll(forceIncludeClassSet);
        //将强制排除从包含列表删除
        includeClassSet.removeAll(forceExcludeClassSet);
        //将排除从包含列表删除
        includeClassSet.removeAll(excludeClassSet);
        for (Class clazz : includeClassSet) {
            mixMap.put(clazz, AntPathFilterMixin.class);
        }
        return mixMap;
    }

    /**
     * 通过包名获取包下的所有类
     *
     * @param packageNames 包名
     */
    private Set<Class<?>> getClasses(String[] packageNames) {
        if (packageNames == null || packageNames.length == 0) {
            return Collections.emptySet();
        }
        return Arrays.stream(packageNames).map(packageName -> {
            Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
            return reflections.getSubTypesOf(Object.class);
        }).flatMap(Collection::stream).collect(Collectors.toSet());
    }

}
