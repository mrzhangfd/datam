package com.sdu.inas.datam.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应Mongodb中的newperson
 *
 * @author icatzfd
 * Created on 2020/5/21 16:49.
 */
@Data
public class NewPerson {
    //长id
    private String _id;

    //姓名
    private String figureName;

    //介绍
    private String intro;

    //事件集合
    private List<String> events = new ArrayList<>();

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
