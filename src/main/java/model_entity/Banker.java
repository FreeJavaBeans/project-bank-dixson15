package model_entity;

public class Banker extends Employee{

    public final static String [] EMP_TYPE  = {"BANK_TELLER", "MANGER"};

    private String type;

    public Banker(int id, String firstName, String lastName, String type) {
        super(id, firstName, lastName);
        this.setType(type);
    }

    public void setType(String type) {

        switch (type){
            case "BANK_TELLER":
                this.type = EMP_TYPE[0];
                break;
            case "MANAGER":
                this.type = EMP_TYPE[1];
                break;
            default:
                System.out.println("Type not specified");
        }

    }
    public String getType() {
        return type;
    }
}
