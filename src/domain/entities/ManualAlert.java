package domain.entities;
public class ManualAlert extends Alert {
    private User createdBy;


    @Override
    public void generateAlert() {
        super.generateAlert();
        System.out.println("Manual alert created by: " + createdBy.getName());
    }
}
