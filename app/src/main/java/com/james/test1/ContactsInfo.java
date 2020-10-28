package com.james.test1;

public class ContactsInfo {
    private  String name;
    private  String idnum;
    private  String account;
    private  String password;
    private  String email;

    public ContactsInfo() {
    }

    public ContactsInfo(String name,String idnum,String account,String password,String email){
        this.name = name;
        this.idnum = idnum;
        this.account = account;
        this.password = password;
        this.email = email;

    }

    public String getName(){
        return this.name = name;
    }
    public String getidnum(){
        return this.idnum = idnum;
    }
    public String getaccount(){
        return this.account = account;
    }
    public String getpassword(){
        return this.password = password;
    }
    public String getemail(){
        return this.email = email;
    }
}
