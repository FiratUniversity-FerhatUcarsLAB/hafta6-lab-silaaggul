Ad Soyad: Sıla Ağgül
Numara:250541020
Proje: Restoran Sipariş
Tarih: 24.11.2025


import java.util.Scanner;

public class AkilliRestoranSistemi {

    // 1) Ana yemek fiyatı (1-4)
    public static double getMainDishPrice(int secim) {
        double price;
        switch (secim) {
            case 1: // Izgara Tavuk
                price = 85;
                break;
            case 2: // Adana Kebap
                price = 120;
                break;
            case 3: // Levrek
                price = 110;
                break;
            case 4: // Mantı
                price = 65;
                break;
            default:
                price = 0; // 0 veya geçersiz seçim
                break;
        }
        return price;
    }

    // 2) Başlangıç fiyatı (0-3) - 0: alınmadı
    public static double getAppetizerPrice(int secim) {
        double price;
        switch (secim) {
            case 0:
                price = 0;
                break;
            case 1: // Çorba
                price = 25;
                break;
            case 2: // Humus
                price = 45;
                break;
            case 3: // Sigara Böreği
                price = 55;
                break;
            default:
                price = 0;
                break;
        }
        return price;
    }

    // 3) İçecek fiyatı (0-4) - 0: alınmadı
    public static double getDrinkPrice(int secim) {
        double price;
        switch (secim) {
            case 0:
                price = 0;
                break;
            case 1: // Kola
                price = 15;
                break;
            case 2: // Ayran (PDF’de 12 yazmış ama aralık 15-45 demiş; biz PDF’e uyalım)
                price = 12;
                break;
            case 3: // Taze Meyve Suyu
                price = 35;
                break;
            case 4: // Limonata
                price = 25;
                break;
            default:
                price = 0;
                break;
        }
        return price;
    }

    // 4) Tatlı fiyatı (0-3) - 0: alınmadı
    public static double getDessertPrice(int secim) {
        double price;
        switch (secim) {
            case 0:
                price = 0;
                break;
            case 1: // Künefe
                price = 65;
                break;
            case 2: // Baklava
                price = 55;
                break;
            case 3: // Sütlaç
                price = 35;
                break;
            default:
                price = 0;
                break;
        }
        return price;
    }

    // 5) Combo mu? Ana + içecek + tatlı seçili mi?
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6) Happy hour mu? (14-17 arası, 17 dahil olmasın diyelim: 14, 15, 16)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat < 17;
    }

    // 7) İndirim hesaplama
    // combo: true/false
    // ogrenci: true/false
    // saat: happy hour için lazım
    // gun: 1-7 (hafta içi / sonu kontrolü)
    public static double calculateDiscount(double araTutar, boolean combo, boolean ogrenci,
                                           int saat, int gun, double icecekTutar) {

        double toplamIndirim = 0.0;

        // 1) Combo indirim (%15)
        if (combo) {
            double comboIndirim = araTutar * 0.15;
            toplamIndirim += comboIndirim;
        }

        // 2) Happy Hour içecek indirimi (%20, sadece içecek kısmına)
        // iç içe if: önce happy hour mı, sonra içecek var mı diye bakabiliriz
        if (isHappyHour(saat)) {
            if (icecekTutar > 0) {
                double happyHourIndirim = icecekTutar * 0.20;
                toplamIndirim += happyHourIndirim;
            }
        }

        // 3) Öğrenci indirimi: Hafta içi (1-5) %10 ekstra indirim
        // (Öncekilerin üstüne ekleniyor)
        boolean haftaIci = gun >= 1 && gun <= 5;
        if (ogrenci) {
            if (haftaIci) {
                double ogrenciIndirim = (araTutar - toplamIndirim) * 0.10;
                // Not: örnekteki gibi indirimin indirime uygulanması için
                toplamIndirim += ogrenciIndirim;
            }
        }

        // 4) 200 TL üzeri %10 indirim (tüm diğer indirimlerden sonra)
        double araToplamIndirimli = araTutar - toplamIndirim;
        if (araToplamIndirimli > 200) {
            double ekstraIndirim = araToplamIndirimli * 0.10;
            toplamIndirim += ekstraIndirim;
        }

        return toplamIndirim;
    }

    // 8) Bahşiş önerisi: %10
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== Akilli Restoran Siparis Sistemi ===");

        // Ana yemek (zorunlu gibi düşünebiliriz, ama 0 dersen 0 TL olur)
        System.out.print("Ana Yemek Secimi (0=Yok, 1=Izgara Tavuk, 2=Adana Kebap, 3=Levrek, 4=Manti): ");
        int anaSecim = input.nextInt();

        System.out.print("Baslangic Secimi (0=Yok, 1=Corba, 2=Humus, 3=Sigara Boregi): ");
        int baslangicSecim = input.nextInt();

        System.out.print("Icecek Secimi (0=Yok, 1=Kola, 2=Ayran, 3=Taze Meyve Suyu, 4=Limonata): ");
        int icecekSecim = input.nextInt();

        System.out.print("Tatli Secimi (0=Yok, 1=Kunefe, 2=Baklava, 3=Sutlac): ");
        int tatliSecim = input.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = input.nextInt();

        System.out.print("Ogrenci misiniz? (E/H): ");
        char ogrenciCevap = input.next().charAt(0);
        boolean ogrenci = (ogrenciCevap == 'E' || ogrenciCevap == 'e');

        System.out.print("Gun (1=Pzt, 2=Sal, 3=Car, 4=Per, 5=Cuma, 6=Cmt, 7=Paz): ");
        int gun = input.nextInt();

        // Fiyatları al
        double anaFiyat = getMainDishPrice(anaSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        double araTutar = anaFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        // Boolean ile combo kontrolü
        boolean anaVar = anaFiyat > 0;
        boolean icecekVar = icecekFiyat > 0;
        boolean tatliVar = tatliFiyat > 0;

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        // İndirimleri hesapla
        double toplamIndirim = calculateDiscount(araTutar, combo, ogrenci, saat, gun, icecekFiyat);
        double odenecekTutar = araTutar - toplamIndirim;

        // Bahşiş önerisi (%10)
        double bahsisOneri = calculateServiceTip(odenecekTutar);

        // ÇIKTI
        System.out.println("\n===== Siparis Ozeti =====");
        System.out.printf("Ara Tutar         : %.2f TL%n", araTutar);

        System.out.printf("Toplam Indirim    : %.2f TL%n", toplamIndirim);
        System.out.printf("Odenecek Tutar    : %.2f TL%n", odenecekTutar);
        System.out.printf("Bahsis Onerisi %%10: %.2f TL%n", bahsisOneri);

        System.out.println("=========================");

        input.close();
    }
}
