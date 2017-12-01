package tw.idv.jameschang.baseadapterdemo;

/**
 * Created by cyy on 2017/12/1.
 */

public class UserDataModel {
    String name;
    int age;
    String sport;
    String sex;


    public UserDataModel(String name, int age, String sport, String sex) {
        this.name = name;
        this.age = age;
        this.sport = sport;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSport() {
        return sport;
    }

    public String getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
