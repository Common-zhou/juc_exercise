package com.beiwu.ch5.map;

import java.util.Objects;

/**
 * @author zhoubing
 * @date 2021-01-04 22:22
 */
public class KeyVo {
    private final int id;
    private final String name;

    public KeyVo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KeyVo keyVo = (KeyVo) o;
        return id == keyVo.id &&
                Objects.equals(name, keyVo.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
