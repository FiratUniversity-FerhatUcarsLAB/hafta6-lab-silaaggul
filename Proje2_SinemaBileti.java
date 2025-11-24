Ad Soyad: Sıla Ağgül
Numara:250541020
Proje: Sinema Bileti
Tarih: 24.11.2025


import java.util.Scanner;

public class SinemaBiletiSistemi {

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        // 6 = Cumartesi, 7 = Pazar
        return gun == 6 || gun == 7;
    }

    // 2) Matine mi? (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel bilet fiyatını hesapla
    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        double basePrice;

        if (!haftaSonu && matine) {
            // Hafta içi matine
            basePrice = 45;
        } else if (!haftaSonu) {
            // Hafta içi normal
            basePrice = 65;
        } else if (haftaSonu && matine) {
            // Hafta sonu matine
            basePrice = 55;
        } else {
            // Hafta sonu normal
            basePrice = 85;
        }

        return basePrice;
    }

    // 4) İndirim oranını hesapla (sadece yüzde ORANI döndürüyoruz, örn: 0.20)
    public static double calculateDiscount(int yas, int meslek, int gun) {
        // meslek: 1 = Ogrenci, 2 = Ogretmen, 3 = Diger
        // gun: 1=Pzt ... 7=Paz

        double discountRate = 0.0;

        // Yaş indirimleri (her gün geçerli)
        if (yas >= 65) {
            discountRate = 0.30; // 65+ yaş %30
        } else if (yas < 12) {
            discountRate = 0.25; // 12 yaş altı %25
        } else {
            // Öğrenci indirimleri
            if (meslek == 1) { // Öğrenci
                // Pazartesi(1) - Perşembe(4)
                if (gun >= 1 && gun <= 4) {
                    discountRate = 0.20; // %20
                } else {
                    // Cuma(5) - Pazar(7)
                    discountRate = 0.15; // %15
                }
            }

            // Öğretmen indirimi (sadece Çarşamba)
            if (meslek == 2 && gun == 3) { // 3 = Çarşamba
                // Öğretmen indirimi %35
                // Not: Eğer öğrenci indirimi ile çakışırsa hangisi
                // geçerli olsun net değil, biz en yüksek indirimi uygulayalım:
                if (0.35 > discountRate) {
                    discountRate = 0.35;
                }
            }
        }

        return discountRate;
    }

    // 5) Film türüne göre ekstra ücret
    public static double getFormatExtra(int filmTuru) {
        // filmTuru: 1=2D, 2=3D, 3=IMAX, 4=4DX
        double extra = 0.0;

        switch (filmTuru) {
            case 1: // 2D
                extra = 0.0;
                break;
            case 2: // 3D
                extra = 25.0;
                break;
            case 3: // IMAX
                extra = 35.0;
                break;
            case 4: // 4DX
                extra = 50.0;
                break;
            default:
                extra = 0.0;
                break;
        }

        return extra;
    }

    // 6) Nihai fiyatı hesapla
    public static double calculateFinalPrice(double basePrice, double discountRate, double formatExtra) {
        double discountAmount = basePrice * discountRate;
        double discountedPrice = basePrice - discountAmount;
        double finalPrice = discountedPrice + formatExtra;
        return finalPrice;
    }

    // 7) Bilet bilgisi yazdırma (rapor oluşturma)
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru,
                                          double basePrice, double discountRate, double formatExtra, double finalPrice) {

        System.out.println("\n===== Sinema Bileti =====");

        // Gün adı
        String gunAdi;
        switch (gun) {
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Sali"; break;
            case 3: gunAdi = "Carsamba"; break;
            case 4: gunAdi = "Persembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
            default: gunAdi = "Gecersiz Gun"; break;
        }

        // Meslek adı
        String meslekAdi;
        switch (meslek) {
            case 1: meslekAdi = "Ogrenci"; break;
            case 2: meslekAdi = "Ogretmen"; break;
            case 3: meslekAdi = "Diger"; break;
            default: meslekAdi = "Bilinmiyor"; break;
        }

        // Film türü adı
        String filmTuruAdi;
        switch (filmTuru) {
            case 1: filmTuruAdi = "2D"; break;
            case 2: filmTuruAdi = "3D"; break;
            case 3: filmTuruAdi = "IMAX"; break;
            case 4: filmTuruAdi = "4DX"; break;
            default: filmTuruAdi = "Bilinmiyor"; break;
        }

        System.out.printf("Gun        : %s%n", gunAdi);
        System.out.printf("Saat       : %02d:00%n", saat);
        System.out.printf("Yas        : %d%n", yas);
        System.out.printf("Meslek     : %s%n", meslekAdi);
        System.out.printf("Film Turu  : %s%n", filmTuruAdi);

        System.out.printf("Temel Fiyat     : %.2f TL%n", basePrice);
        System.out.printf("Indirim Orani   : %.0f%%%n", discountRate * 100);
        System.out.printf("Format Ekstra   : %.2f TL%n", formatExtra);
        System.out.printf("Odenecek Tutar  : %.2f TL%n", finalPrice);

        System.out.println("=========================");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Girdiler
        System.out.print("Gun (1=Pzt, 2=Sal, 3=Car, 4=Per, 5=Cuma, 6=Cmt, 7=Paz): ");
        int gun = input.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = input.nextInt();

        System.out.print("Yas: ");
        int yas = input.nextInt();

        System.out.print("Meslek (1=Ogrenci, 2=Ogretmen, 3=Diger): ");
        int meslek = input.nextInt();

        System.out.print("Film Turu (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = input.nextInt();

        // Hesaplamalar
        double basePrice = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double formatExtra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(basePrice, discountRate, formatExtra);

        // Bilet bilgisi yazdır
        generateTicketInfo(gun, saat, yas, meslek, filmTuru,
                           basePrice, discountRate, formatExtra, finalPrice);

        input.close();
    }
}

  
