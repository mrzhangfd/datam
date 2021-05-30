package com.sdu.inas.datam.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应Mongdb中的event表
 * 集合Collection = 表 Table
 * 文档Document = 行 Row
 *
 * @author J
 */

@Document(collection = "event")
@Data
public class Doc {
    @Id
    String _id;
    String date;
    List<String> pName = new ArrayList<>();
    List<String> sName = new ArrayList<>();
    String details;
    String event;

    public Doc(String _id,String date, List<String> pName, List<String> sName, String details) {
        this._id=_id;
        this.date = date;
        this.pName = pName;
        this.sName = sName;
        this.details = details;
    }

    public Doc() {
    }

    public static void main(String[] args) {
        for(int i=0;i<2020;i++){
            System.out.println(String.format("%0" + 4 + "d", i));
        }
    }
}
