package util;

public class ISBNChecker {
    public static boolean isGeldigISBN(String isbn){
        if(isbn.length() == 13){ // isbn is altijd 13 cijfers
            if(isbn.startsWith("978") || isbn.startsWith("979")){
                int som = 0;
                int tiental = 0;
                for(int i = 0; i< 12;i++){
                    int cijfer = Integer.parseInt(isbn.substring(i,i+1));
                    som += (i+1) % 2 == 0 ? cijfer * 3 : cijfer; // i+1, de check telt vanaf de eerste positie, niet 0
                }
                int lengte = String.valueOf(som).length();
                if(String.valueOf(som).substring(lengte-1,lengte).equals("0")){
                    tiental = som;
                }else {
                    tiental = ((som / 10) * 10) + 10;
                }
                return(Integer.parseInt(isbn.substring(12,13)) == (tiental - som));
            }
        }
        return false;
    }
}
