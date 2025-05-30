package domain.entities;
public class AutomaticAlert extends Alert {
    private WheatherData generatedFrom;

    @Override
    public void generateAlert() {
        super.generateAlert();
        System.out.println("Automatic alert generated from weather data with ID: " + generatedFrom.getId());
    }

}
