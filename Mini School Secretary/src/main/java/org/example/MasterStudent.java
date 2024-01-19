package org.example;

public class MasterStudent extends Student {
    public MasterStudent(String name, String studyCycle) {
        super(name, studyCycle);
    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public float getGrade() {
        return super.getGrade();
    }
    public void setGrade(float grade) {
        super.setGrade(grade);
    }
}
