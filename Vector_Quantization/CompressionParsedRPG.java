package Vector_Quantization;

public class CompressionParsedRPG {
    private CompressionParsed red;
    private CompressionParsed green;
    private CompressionParsed blue;
    public CompressionParsedRPG(CompressionParsed red, CompressionParsed green, CompressionParsed blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    public CompressionParsed getRed() {
        return red;
    }
    public void setRed(CompressionParsed red) {
        this.red = red;
    }
    public CompressionParsed getGreen() {
        return green;
    }
    public void setGreen(CompressionParsed green) {
        this.green = green;
    }
    public CompressionParsed getBlue() {
        return blue;
    }
    public void setBlue(CompressionParsed blue) {
        this.blue = blue;
    }
}
