package Model;

public class Person {
    private String name;
    private String lname;
    private String address;
    private int age;

    public Person() {
    }

    public Person(String name, String lname, String address, int age) {
        this.name = name;
        this.lname = lname;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
