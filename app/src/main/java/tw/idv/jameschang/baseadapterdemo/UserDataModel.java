package tw.idv.jameschang.baseadapterdemo;

/**
 * Created by cyy on 2017/12/1.
 */

public class UserDataModel {
    String account;
    int age;
    String sport;
    String sex;

    public UserDataModel() {
    }

//    public UserDataModel(String account, int age, String sport, String sex) {
//        this.account = account;
//        this.age = age;
//        this.sport = sport;
//        this.sex = sex;
//    }

    public String getAccount() {

        return account;
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

    public void setAccount(String account) {
        this.account = account;
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
