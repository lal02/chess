package settings;

public enum Background {
    LIGHTGREEN("#7D945D","/resources/images/boards/light_greenBoard.png"),
    DARKGREEN("#0B2D10","/resources/images/boards/dark_greenBoard.png"),
    DARKBLUE("#182387","/resources/images/boards/dark_blueBoard.png"),
    LIGHTBLUE("#32A2CA","/resources/images/boards/light_blueBoard.png"),
    DARKRED("#640404","/resources/images/boards/dark_redBoard.png"),
    LIGHTRED("#DF2B2B","/resources/images/boards/light_redBoard.png"),
    PINK("#A113D1", "/images/boards/pinkBoard.png"),
    PURPLE("#3E17B2","/resources/images/boards/purpleBoard.png"),
    BROWN("#654321","/resources/images/boards/brownBoard.png");


    private final String color;
    private final String path;

    Background(String color,String path) {
        this.color = color;
        this.path = path;
    }

    public String getColor() {
        return color;
    }
    public String getPath() {
        return path;
    }
}
