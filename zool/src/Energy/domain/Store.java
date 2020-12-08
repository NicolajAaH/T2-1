package Energy.domain;

class Store extends Room {
    //Constructorder der kalder overklassens constructor
    public Store(String description, String name) {
        super(description, name);
    }

    @Override
    public String getInvDescriptionString() {
        return "Du kan købe:\n";
    }
}
