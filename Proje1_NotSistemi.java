Ad Soyad: Sıla Ağgül
Numara:250541020
Proje: Not Sistemi
Tarih: 24.11.2025


import java.util.Scanner;

public class OgrenciNotSistemi {

    // 1) Ortalama hesaplama metodu
    public static double calculateAverage(double vize, double finall, double odev) {
        // Vize %30, Final %40, Ödev %30
        double ortalama = vize * 0.30 + finall * 0.40 + odev * 0.30;
        return ortalama;
    }

    // 2) Geçme / kalma durumu (ortalama >= 50 ise geçti)
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    // 3) Harf notu hesaplama metodu
    public static char getLetterGrade(double ortalama) {
        char harf;

        if (ortalama >= 90 && ortalama <= 100) {
            harf = 'A';
        } else if (ortalama >= 80) { // 80-89
            harf = 'B';
        } else if (ortalama >= 70) { // 70-79
            harf = 'C';
        } else if (ortalama >= 60) { // 60-69
            harf = 'D';
        } else {                     // 0-59
            harf = 'F';
        }

        return harf;
    }

    // 4) Onur listesi kontrolü:
    // Ortalama >= 85 VE hiçbir not < 70 olmamalı
    public static boolean isHonorList(double ortalama, double vize, double finall, double odev) {
        boolean ortalamaYeterliMi = ortalama >= 85;
        boolean notlarYeterliMi = (vize >= 70) && (finall >= 70) && (odev >= 70);

        return ortalamaYeterliMi && notlarYeterliMi;
    }

    // 5) Bütünleme hakkı:
    // 40 ≤ ortalama < 50
    public static boolean hasRetakeRight(double ortalama) {
        return (ortalama >= 40) && (ortalama < 50);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // GİRİŞLER
        System.out.print("Vize notunu giriniz (0-100): ");
        double vize = input.nextDouble();

        System.out.print("Final notunu giriniz (0-100): ");
        double finall = input.nextDouble();

        System.out.print("Odev notunu giriniz (0-100): ");
        double odev = input.nextDouble();

        // ORTALAMA HESAPLAMA
        double ortalama = calculateAverage(vize, finall, odev);

        // KARARLAR
        boolean gectiMi = isPassingGrade(ortalama);
        char harfNotu = getLetterGrade(ortalama);
        boolean onurListesindeMi = isHonorList(ortalama, vize, finall, odev);
        boolean butHakkiVarMi = hasRetakeRight(ortalama);

        // ÇIKTI – printf ile düzenli format
        System.out.println("\n===== Ogrenci Not Raporu =====");
        System.out.printf("Vize Notu  : %.2f%n", vize);
        System.out.printf("Final Notu : %.2f%n", finall);
        System.out.printf("Odev Notu  : %.2f%n", odev);
        System.out.printf("Ortalama   : %.2f%n", ortalama);

        // Geçme / kalma durumu
        if (gectiMi) {
            System.out.println("Durum      : GECTI");
        } else {
            System.out.println("Durum      : KALDI");
        }

        // Harf notu
        System.out.println("Harf Notu  : " + harfNotu);

        // Onur listesi
        if (onurListesindeMi) {
            System.out.println("Onur Listesi: EVET");
        } else {
            System.out.println("Onur Listesi: HAYIR");
        }

        // Bütünleme hakkı
        if (butHakkiVarMi) {
            System.out.println("Butunleme Hakkı: VAR");
        } else {
            System.out.println("Butunleme Hakkı: YOK");
        }

        System.out.println("==============================");

        input.close();
    }
}
