package com.itlab1024.protobuf;


import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.InvalidProtocolBufferException;
import com.itlab1024.protobuf.protos.java.AddressBook;
import com.itlab1024.protobuf.protos.java.Person;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        Person person = Person.newBuilder().setId(1).setEmail("itlab1024@163.com")
                .setName("IT实验室")
                .addPhones(Person.PhoneNumber.newBuilder().setNumber("13648886666").setType(Person.PhoneType.MOBILE).build()).build();
        AddressBook addressBook = AddressBook.newBuilder().addPeople(person).build();
        // 序列化
        byte[] bytes = addressBook.toByteArray();
        System.out.println(Arrays.toString(bytes));
        // 反序列化
        AddressBook book = AddressBook.parseFrom(bytes);
        List<Person> peoples = book.getPeopleList();
        for (Person people : peoples) {
            System.out.println("姓名：" + people.getName());
            System.out.println("邮箱：" + people.getEmail());
            System.out.println("手机号个数：" + people.getPhonesCount());
        }
    }
}
