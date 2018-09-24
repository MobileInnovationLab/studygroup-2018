# Modul 1 : Membuat Layout dan UI Interaktif

### Kalian akan belajar

Menggunakan editor layout di Android Studio
Memposisikan tampilan di RelativeLayout
Memposisikan tampilan di ConstraintLayout
Membuat varian layout untuk orientasi lanskap dan tampilan yang lebih besar

### 1. Membuat Layout
Dalam praktik ini kalian akan mendesain dan mengimplementasikan proyek untuk aplikasi "Hello Toast".

#### 1. Ubah group tampilan ke LinearLayout
Saat ini, group tampilan kalian akan menggunakan constraint layout, cobalah menggantinya menjadi LinearLayout
contoh : 
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context="hellotoast.android.example.com.hellotoast.MainActivity">
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!" />
</LinearLayout>
```

#### 2. Menambahkan elemen ke tampilan
Masukkan 3 elemen ke tampilan, yang harus anda masukkan adalah
Jangan lupa untuk memberi ID unik ke setiap elemen
Selain itu berikan juga width dan height untuk setiap elemen
Match Parent -> Elemen akan berukuran sesuai width/height grup tampilan
Wrap content -> Elemen akan berukuran sesuai kebutuhan elemennya

| Elemen        | Teks          |
| --------------|---------------|
| Button        | Toast         |
| Text View     | 0             |
| Button        | Count         |

#### 3. Membuat sumber daya string
Daripada melakukan hard-code string ke dalam kode XML, sebaiknya gunakan sumber daya string, yang mewakili string tersebut.
**Mengapa**: String yang ada di file yang terpisah lebih mudah dikelola, terutama jika Anda menggunakan string ini lebih dari sekali. Selain itu, sumber daya string wajib ada untuk menerjemahkan dan melokalisasi aplikasi karena Anda akan membuat file sumber daya string untuk setiap bahasa.

1. Letakkan kursor pada kata"Toast".
2. Tekan Alt-Enter (Option-Enter di Mac).
3. Pilih Extract string resources.
4. Setel nama Sumber Daya ke button_label_toast dan klik OK. (Jika Anda membuat kesalahan, batalkan perubahan dengan Ctrl-Z.)
```
@string/button_label_toast
```
5. Untuk setiap elemen yang menggunakan string, gunakan langkah 1-4 agar semuanya tersimpan di Sumber Daya String
6. Dalam tampilan Proyek, buka values/strings.xml untuk menemukan string Anda. Sekarang Anda bisa mengedit semua string di satu tempat.

Proses ini membuat sumber daya string dalam file values/res/string.xml, dan string dalam kode Anda digantikan dengan referensi ke sumber daya string tersebut.

#### 4. Mengubah Ukuran
Sama dengan string, sebaiknya ekstrak dimensi tampilan dari file XML layout utama ke dalam sumber daya dimensi yang terletak di dalam file.
**Mengapa**: Hal ini akan memudahkan pengelolaan dimensi, terutama jika Anda perlu menyesuaikan layout untuk resolusi perangkat yang berbeda. Ini juga akan memudahkan pengaturan ukuran yang konsisten, dan mengubah ukuran beberapa objek dengan mengubah satu properti.

#### 5. Menyetel warna dan latar belakang
1. Ubah ukuran TextView count
```
android:textSize="160sp"
```
"sp" adalah singkatan dari piksel yang tidak tergantung pada skala, dan seperti dp, ini adalah unit yang skalanya menyesuaikan dengan kepadatan layar dan preferensi ukuran font pengguna.

2. Ekstrak ukuran teks TextView sebagai sumber daya dimensi bernama count_text_size, sebagai berikut:
a. Klik tab Text untuk menunjukkan kode XML, jika belum.
b. Letakkan kursor pada "160sp".
c. Tekan Alt-Enter (Option-Enter di Mac).
d. Klik Extract dimension resource.
e. Setel Resource nameke count_text_size dan klik OK. (Jika Anda membuat kesalahan, batalkan perubahan dengan Ctrl-Z).
f. Dalam tampilan Proyek, buka values/dimens.xml untuk menemukan dimensi Anda. File dimens.xml file berlaku untuk semua perangkat. File dimens.xml file untuk w820dp hanya berlaku untuk perangkat yang lebih lebar dari 820dp.

3. Ubah textStyle dari TextView show_count ke cetak tebal.
```
android:textStyle="bold"
```

4. Mengubah warna

a. Ubah warna teks dalam tampilan teks show_count ke warna utama tema(colorPrimary). Lihat file sumber daya colors.xml untuk melihat bagaimana warna yang sudah di ada.
```
android:textColor="@color/colorPrimary"
```

b. Ubah teks dalam button ke warna putih
```
android:textColor="@android:color/white"
```

c. Tambahkan warna latar belakang textview
```
android:background="#FFFF00"
```
d. Dalam Layout Editor (tab Text), arahkan kursor ke warna ini dan tekanAlt-Enter (Option-Enter di Mac).
e. Pilih Choose color, yang akan memunculkan pemilih warna, pilih warna yang Anda suka, atau pilih warna kuning yang sudah dipakai sekarang, lalu klik Choose.
f. Buka values/colors.xml. Perhatikan bahwa colorPrimary yang Anda gunakan sebelumnya ditetapkan di sini.
g. Menggunakan warna dalam values/colors.xml sebagai contoh, tambahkan sumber daya bernama myBackgroundColor untuk warna latar belakang, lalu gunakan untuk menyetel latar belakang tampilan teks.
```
<color name="myBackgroundColor">#FFF043</color>
```

### Challange
Lanjutkan pekerjaan anda yang sebelumnya, dan buatlah tampilan agar mirip seperti dibawah
![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/1_2A_P_images/dg_weight_gravity.png)

**Hint**
1. Android : atribut layout_gravity menentukan bagaimana tampilan diratakan dalam Tampilan induk. Karena lebar tampilan cocok dengan induknya, menyetelnya secara eksplisit tidak diperlukan. Anda dapat memusatkan tampilan sempit secara horizontal dalam induknya: android:layout_gravity="center_horizontal"

2. android:atribut layout_weight menunjukkan berapa banyak ruang ekstra dalam LinearLayout yang akan dialokasikan ke tampilan yang sudah menyetel parameter ini. Jika hanya satu tampilan yang memiliki atribut ini, tampilan itu akan mendapatkan semua ruang layar ekstra. Untuk beberapa tampilan, ruangan dipro-rata. Misalnya, jika kedua tombol memiliki berat 1 dan tampilan teks memiliki berat 2, sehingga totalnya 4, masing-masing tombol mendapatkan ¼ ruang, dan tampilan teks mendapatkan setengah.

3. Android:atribut gravity menentukan perataan konten Tampilan di dalam Tampilan itu sendiri. Kounter dipusatkan dalam tampilannya dengan: android:gravity="center"



### 2. Menambahkan Handler onclick untuk tombol
Handler klik adalah metode yang dipanggil saat pengguna mengeklik elemen antarmuka pengguna. 
Dalam Android, Ada banyak cara dalam membuat handler salah satunya adalah dengan
menambahkan properti android:onClick di elemen pada file xml kalian.

1. Buka res/layout/activity_main.xml.


2. Tambahkan properti berikut ke tombol toast
```
android:onClick="showToast"
```


3. Tambahkan properti berikut ke tombol count
```
android:onClick="countUp"
```


4. Dalam activity_main.xml, letakkan kursor mouse Anda di atas setiap nama metode ini.


5. Tekan Alt-Enter (Option-Enter di Mac), dan pilih Create onClick event handler.


6. Pilih MainActivity dan klik OK.


7. Buka MainActivity.java
Buat 2 fungsi
```java
   public void countUp(View view) {
        // What happens when user clicks on the button_count Button goes here.
   }


   public void showToast(View view) {
        // What happens when user clicks on the button_toast Button goes here.
   }
```

### Challange
1. Isilah fungsi countUp dan showToast.
fungsi countUp akan mengambil nilai dari textview lalu mengincrement nilai tersebut dan kemudian melakukan set ulang nilai yang baru
ke textview

fungsi showToast akan menunjukkan popup kecil yang menyatakan "Hello, Toast"
**Hint** 
Gunakan 
```
    Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG;);
    toast.show();
```
