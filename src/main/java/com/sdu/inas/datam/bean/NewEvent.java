package com.sdu.inas.datam.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应Mongodb中的newevent
 *
 * @author icatzfd
 * Created on 2020/5/21 16:48.
 */
@Data
public class NewEvent {
    private String _id;
    private String obj_id;
    private String date;
    private String eventIntro;
    private String eventDetail;
    private List<String> pName = new ArrayList<>();
    private List<String> sName = new ArrayList<>();

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
