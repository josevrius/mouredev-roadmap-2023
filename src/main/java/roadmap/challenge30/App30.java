package roadmap.challenge30;

public final class App30 {

    private static final String COLUMNS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String HEADER = """
            
            LA COLUMNA DE EXCEL
            ===================""";

    public static void main(String[] args) {
        String column = "XFD".toUpperCase();

        try {
            System.out.println(HEADER);
            int index = getColumnIndex(column);
            System.out.println("Columna : " + column);
            System.out.println("Índice .: " + index);
        } catch (Exception e) {
            System.err.println("Error ..: " + e.getMessage());
        }
    }

    private static int getColumnIndex(String column) {
        if (!column.matches("[A-Z]+")) {
            throw new IllegalArgumentException("Nombre de columna desconocido");
        }
        int index = 0;

        for (char c : column.toCharArray()) {
            index = index * COLUMNS.length() + COLUMNS.indexOf(c) + 1;
        }
        return index;
    }
}
