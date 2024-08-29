# Inventory Management System

## Açıklama

Bu proje, bir envanter yönetim sistemi uygulamasını içerir. Uygulama, ürünlerin eklenmesi, görüntülenmesi, güncellenmesi ve silinmesini sağlar. Ayrıca, stok durumu ve son kullanma tarihi yaklaşan ürünleri kontrol edebilir.

## Özellikler

- Ürün Ekleme
- Ürün Görüntüleme
- Ürün Güncelleme
- Ürün Silme
- Düşük Stok Uyarısı
- Yaklaşan Son Kullanma Tarihi Uyarısı
- Toplam Ürün Sayısı
- Toplam Envanter Değeri

## Gereksinimler

- Java 8 veya üzeri
- SQL Server
- IDE (Eclipse, IntelliJ vb.)

## Kurulum

1. **Proje İndirme**:
   ```bash
   git clone https://github.com/bugraistek/Inventory_Management_System.git

## 2. Veritabanı Oluşturma

SQL Server Management Studio veya benzeri bir araç kullanarak `inventory_db` adlı bir veritabanı oluşturun.

Bu veritabanında aşağıdaki `products` tablosunu oluşturun:
   ```sql
   CREATE TABLE products (
       id INT PRIMARY KEY NULL,
       name NVARCHAR(100) NOT NULL,
       category NVARCHAR(100) NOT NULL,
       quantity INT NOT NULL,
       price FLOAT NOT NULL,
       expiry_date DATE NOT NULL );

## 3. Veritabanı Yapılandırması

`DBConnection` sınıfındaki `URL` değişkenini kendi veritabanı yapılandırmanıza göre güncelleyin. Örneğin, sunucu adı, veritabanı adı ve bağlantı ayarlarını kendi SQL Server ortamınıza uygun şekilde düzenleyin.

## 4. Eclipse'te Projeyi İçe Aktarma

Eclipse'te projeyi aşağıdaki adımları izleyerek içe aktarın:

1. **File** > **Import** > **Existing Projects into Workspace** menüsüne gidin.
2. Projenin bulunduğu dizini seçin ve **Finish** butonuna tıklayın.

## 5. Projeyi Çalıştırma

Projeyi Eclipse'te çalıştırmak için:

1. `Main` sınıfını sağ tıklayın.
2. **Run As** > **Java Application** seçeneğini seçin.
3. Program başladığında komut satırı menüsünü kullanarak uygulama özelliklerini test edebilirsiniz.

## Kullanım

Proje çalıştırıldığında, aşağıdaki menü seçenekleriyle etkileşim kurabilirsiniz:

1. **Ürün Ekle**: Yeni bir ürün ekleyin.
2. **Ürünleri Görüntüle**: Tüm ürünleri listeleyin.
3. **Ürün Güncelle**: Mevcut bir ürünü güncelleyin.
4. **Ürün Sil**: Bir ürünü silin.
5. **Düşük Stok Ürünlerini Kontrol Et**: Stoğu düşük olan ürünleri listeleyin.
6. **Yaklaşan Son Kullanma Tarihi Ürünlerini Kontrol Et**: Son kullanma tarihi yaklaşan ürünleri listeleyin.
7. **Toplam Ürün Sayısı**: Depodaki toplam ürün sayısını görüntüleyin.
8. **Toplam Envanter Değeri**: Depodaki tüm ürünlerin toplam değerini hesaplayın.
9. **Çıkış**: Uygulamadan çıkış yapın.

## Katkıda Bulunma

Projeye katkıda bulunmak isterseniz, lütfen aşağıdaki adımları izleyin:

1. Bu projeyi GitHub üzerinden çatallayın (`fork`).
2. Yeni bir özellik (`feature`) dalı oluşturun.
3. Yapmak istediğiniz değişiklikleri yapın ve test edin.
4. GitHub üzerinde bir çekme isteği (`pull request`) oluşturun.

