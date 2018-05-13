package com.example.arjun_mu.android;

class MySingleton {

    String name="arjun";

    boolean save;


    private static final MySingleton ourInstance = new MySingleton();


    static MySingleton getInstance() {
        return ourInstance;
    }

    private MySingleton() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

}
