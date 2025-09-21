package roadmap.challenge34;

public final class App34 {

    private static final String HEADER = """
            
            COLORES HEX Y RGB
            =================""";

    private record RGB(int red, int green, int blue) {
        @Override
        public String toString() {
            return String.format("rgb(%d, %d, %d)", red, green, blue);
        }
    }

    public static void main(String[] args) {
        RGB rgb = new RGB(32, 65, 210);
        String hex = "#FFA500";

        try {
            System.out.println(HEADER);
            System.out.printf("%s -> %s%n", rgb, getHexFromRGB(rgb));
            System.out.printf("%s -> %s%n", hex, getRGBFromHex(hex));
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    private static String getHexFromRGB(RGB color) {
        int r = Math.max(0, Math.min(color.red, 255));
        int g = Math.max(0, Math.min(color.green, 255));
        int b = Math.max(0, Math.min(color.blue, 255));

        return String.format("#%02X%02X%02X", r, g, b);
    }

    private static RGB getRGBFromHex(String hex) {
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        if (hex.length() != 6) {
            throw new IllegalArgumentException("Formato hexadecimal incorrecto");
        }

        int r = Integer.parseInt(hex.substring(0, 2), 16);
        int g = Integer.parseInt(hex.substring(2, 4), 16);
        int b = Integer.parseInt(hex.substring(4, 6), 16);

        return new RGB(r, g, b);
    }
}
