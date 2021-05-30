package com.sdu.inas.datam.dao;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sdu.inas.datam.bean.Doc;
import com.sdu.inas.datam.bean.Person;
import com.sdu.inas.datam.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * @author jgy
 */
@Repository
public class MongoDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @SuppressWarnings("unchecked")
    public ArrayList<Doc> getAllDoc() {
        ArrayList<Doc> docs = new ArrayList<>();
        DBCollection dbCollection = (DBCollection) mongoTemplate.getCollection("event");
        DBCursor cursor = dbCollection.find();
        ArrayList<String> pName = new ArrayList<>();
        ArrayList<String> sName = new ArrayList<>();
        int i = 0;
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            pName.clear();
            sName.clear();
            if (object.get("pName") instanceof java.util.List) {
                pName = (ArrayList<String>) object.get("pName");
            } else {
                String p = (String) object.get("pName");
                pName.add(p);
            }
            if (object.get("sName") instanceof java.util.List) {
                sName = (ArrayList<String>) object.get("sName");
            } else {
                String s = (String) object.get("sName");
                sName.add(s);
            }
            i++;
            String details = (String) object.get("event");
            String date = (String) object.get("date");
            String t = CommonUtil.fillTime(date.replace("/", "-"));
            System.out.println("have got: " + String.valueOf(i) + "  " + t + "  " + details);
            ArrayList<String> p = new ArrayList<>(pName);
            ArrayList<String> s = new ArrayList<>(sName);
            String _id=object.get("_id").toString();
            Doc doc = new Doc(_id,t, p, s, details);
            docs.add(doc);
        }
        return docs;
    }

    /**
     * 根据人物姓名获取基础信息
     *
     * @param pName 人物姓名
     * @return 基础信息
     */
    public String getBasicInfo(String pName) {

        List<Person> people = mongoTemplate.find(new Query(Criteria.where("pName").is(pName)), Person.class);
        if (people.size() != 0) {
            System.out.println(people);
            Person person = people.get(0);
            System.out.println(person);
            if (person.getPBaseInfo() == null) {
                return "";
            }
            return person.getPBaseInfo();
        } else {
            return "";
        }
    }

    /**
     * 根据姓名列表，获得包含该姓名的文档集合
     *
     * @param names 姓名列表
     * @return 包含该姓名的文档集合
     */
    public HashSet<String> getDocByNames(ArrayList<String> names) {

        HashSet<String> objects = new HashSet<String>() {
        };
        for (String name : names) {
            DBCollection dbCollection = (DBCollection) mongoTemplate.getCollection("event");
            DBCursor cursor = dbCollection.find();
            ArrayList<String> pName = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                pName.clear();
                if (object.get("pName") instanceof java.util.List) {
                    pName = (ArrayList<String>) object.get("pName");
                    if (pName.contains(name)) {
                        objects.add(object.toString());
                    }
                } else {
                    String p = (String) object.get("pName");
                    if (p.equals(name)) {
                        objects.add(object.toString());
                    }
                }
            }
        }
        return objects;
    }

    /**
     * 根据姓名，获取文档（event）
     * @param pName
     * @return
     */
    public List<Doc> getDocByName(String pName) {

        List<Doc> docs = mongoTemplate.find(new Query(Criteria.where("pName").is(pName)), Doc.class);
        if (docs.size() != 0) {
            System.out.println(docs);
            return docs;
        } else {
            return null;
        }

    }

    /**
     * 根据姓名，获得Person对象
     * @param pName
     * @return
     */
    public Person getPersonByName(String pName){
        String collectionName="person";
        List<Person> persons = mongoTemplate.find(new Query(Criteria.where("pName").is(pName)), Person.class);
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        if (persons.size() != 0) {
            System.out.println("===============================");
            //System.out.println(persons);
            Person person= new Person();
            person= persons.get(0);
            return person;
        } else {
            return null;
        }
    }

    /**
     * 根据姓名列表，获得包含该姓名的文档集合
     *
     * @param names 姓名列表
     * @return 包含该姓名的文档集合
     */
    public HashSet<DBObject> getDBOjectByNames(ArrayList<String> names) {

        HashSet<DBObject> objects = new HashSet<DBObject>() {
        };
        for (String name : names) {
            DBCollection dbCollection = (DBCollection) mongoTemplate.getCollection("event");
            DBCursor cursor = dbCollection.find();
            ArrayList<String> pName = new ArrayList<>();
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                pName.clear();
                if (object.get("pName") instanceof java.util.List) {
                    pName = (ArrayList<String>) object.get("pName");
                    if (pName.contains(name)) {
                        objects.add(object);
                    }
                } else {
                    String p = (String) object.get("pName");
                    if (p.equals(name)) {
                        objects.add(object);
                    }
                }
            }
        }
        return objects;
    }


    /**
     * 获取所有人物
     *
     * @return 人物集合
     */
    public ArrayList<Person> getAllPerson() {
        ArrayList<Person> people = new ArrayList<>();
        DBCollection dbCollection = mongoTemplate.getCollection("person");
        DBCursor cursor = dbCollection.find();

        int i = 0;
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            i++;
            String base = (String) object.get("pBaseInfo");
            String name = (String) object.get("pName");
            System.out.println("have got: " + String.valueOf(i) + "  " + name + "  " + base);
            Person person = new Person(name, null, base);
            people.add(person);
        }

        return people;
    }
}
