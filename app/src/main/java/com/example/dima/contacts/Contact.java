package com.example.dima.contacts;

/**
 * Created by aleksej on 03.03.2018.
 */

// класс для хранения нашего контакта. просто конейнер для удобного манипулирования сущностью("в виде гномика"©)
public class Contact {

    long id;
    String name;
    String phone;
    String birthday;

    public Contact(long id, String name, String phone, String birthday) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday='" + birthday + '\'' +
                '}'+"\n";
    }
}
