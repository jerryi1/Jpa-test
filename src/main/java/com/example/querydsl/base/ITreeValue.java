package com.example.querydsl.base;

import lombok.val;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: gaochen
 * Date: 2019/1/9
 */
public interface ITreeValue {
    Long getId();

    Object getParentId();

    List<? extends ITreeValue> getSonList();

    void setSonList(Set<ITreeValue> iTreeValues);

    //这个是从根目录进行查询的
    static Optional<ITreeValue> merge(Set<? extends ITreeValue> tSet) {
        //查找顶层的
        val maybeRoot = tSet.stream().filter(a -> a.getId() != null && a.getParentId() == null).findFirst();
        if (!maybeRoot.isPresent()) {
            return Optional.empty();
        }
        ITreeValue root = maybeRoot.get();
        root.setSonList(findSon(tSet, root));
        return Optional.of(root);
    }

    static Set<ITreeValue> findSon(Set<? extends ITreeValue> tSet, ITreeValue parent) {
        return tSet.stream().map(v -> {
            if (Objects.equals(v.getParentId(), parent.getId())) {
                //v 作为顶层查找son
                v.setSonList(findSon(tSet, v));
                return v;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }
}
