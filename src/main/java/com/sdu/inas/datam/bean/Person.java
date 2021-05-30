package com.sdu.inas.datam.bean;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应Mongdb中的person表
 * @author J
 */
@Data
public class Person {

    String _id;
    String pName;
    List<String> Names = new ArrayList<>();
    String pBaseInfo;

    public Person() {
    }

    public Person(String pName, List<String> names, String pBasicInfo) {
        this.pName = pName;
        this.Names = names;
        this.pBaseInfo = pBasicInfo;
    }

    /**
     * Mongodb会自动生成ObjectId
     *
     * @author fhp
     */
    public class Oid {

        String $oid;

        public String get$oid() {
            return $oid;
        }

        public void set$oid(String $oid) {
            this.$oid = $oid;
        }

    }
}
