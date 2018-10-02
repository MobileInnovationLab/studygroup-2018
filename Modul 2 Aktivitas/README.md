# Modul 1 : Aktivitas

### Kalian akan belajar

Membuat aplikasi Android baru dengan dua aktivitas.  
Meneruskan beberapa data (string) dari aktivitas utama ke yang kedua menggunakan intent, dan menampilkan data tersebut dalam aktivitas kedua.  
Mengirimkan bit data kedua yang berbeda kembali ke aktivitas utama, menggunakan intent juga.  

### PART 1. Mengirim objek aktivitas
Dalam praktik ini kalian akan mengirimkan objek dari aktivitas 1 ke aktivitas 2

#### 1. Membuat Layout 1
Buatlah sebuah aktivitas dengan layout seperti dibawah 
![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/2_1_P_images/pv_3-main-edittext-new.png) 

#### 2. Definisikan onClick tombol send
Definisikan sebuah fungsi onClick untuk tombol send, sehingga saat di klik, tombol send akan mengambil teks dari
EditText disebelahnya lalu meletakkan teks tersebut ke dalam intent, seperti contoh kode dibawah 

```java
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra("pesan_saya", message);
        startActivity(intent);
```

#### 3. Membuat Layout 2  
Buatlah sebuah aktivitas baru dengan layout seperti dibawah.  
![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/2_1_P_images/pv_5-second-message-final.png) 

**Jangan lupa** untuk membuat tombol kembali di action bar seperti gambar layout di atas
gunakan kode ini untuk menunjukkan tombol kembali  
```java
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);  
```



#### 4. Definisikan response saat masuk ke aktivitas 2
Teks yang kalian kirim dari aktivitas 1 kini telah masuk ke aktivitas 2 melalui intent, yang perlu kalian lakukan
adalah menerima intent tersebut, mengambil teksnya dan menunjukkannya ke TextView.  
Buatlah kode seperti contoh dibawah di metode onCreate

```java
       Intent intent = getIntent();
       String message = intent.getStringExtra("pesan_saya");
       TextView textView = (TextView) findViewById(R.id.text_message);
       textView.setText(message);
```

### Challange 1
Lanjutkan pekerjaan anda yang sebelumnya, dan buatlah fungsi yang sama untuk mengirim pesan dari aktivitas 2 ke aktivitas 1. Gunakan tombol reply text untuk mengirimkan balasan ke aktivitas 1.
**Jangan lupa** untuk menampilkan balasannya di aktivitas 1.

**Hint**
1. Kalian dapat menggunakan metode/cara yang sama dengan aktivitas 1


### PART 2. Daur Hidup dan Keadaan aktivitas
Dalam praktik ini kalian akan memahami siklus hidup aktivitas dan kapan aktivitas dibuat, dijeda, dihentikan, dan dimusnahkan.  

####1. Membuat Log pada metode onCreate()
Dengan proyek yang sama seperti PART 1,  
Buatlah kode seperti dibawah pada metode onCreate().

Log.d("Halo", "-------");
Log.d("Saya lagi di", "onCreate");

####2. Buatlah metode baru untuk callback lain
Buatlah log yang sama untuk metode metode dibawah, kalian bisa melakukan override metode dbawah dengan cara
Alt+Insert -> Override Methods  

- onPause()  
Log.d("Saya lagi di", "onPause");
- onRestart()  
Log.d("Saya lagi di", "onRestart");
- onResume()  
Log.d("Saya lagi di", "onResume");
- onStop()  
Log.d("Saya lagi di", "onStop");
- onDestroy()  
Log.d("Saya lagi di", "onDestroy");

####3. Mengamati Log saat aplikasi berjalan
- Klik Android Monitor di bagian bawah android studio, dan pilih LogCat.
- Mulai amati log cat, carilah pesan yang sudah anda tuliskan, apabila kesulitan mencari Log, gunakan fungsi cari, dan carilah "Saya lagi di", sehingga logcat dapat menyaring.

### Challange 2
Jelaskan metode metode dibawah pada README.md modul 2(Rangkuman) kalian,
Kapan saja metode metode dibawah ini akan dipanggil?

onPause()
onRestart()
onResume()
onStop()
onDestroy()

**Hint**
1. Cobalah bereksperimen dengan aplikasi kalian, jangan lupa untuk memperhatikan logcat
2. Cobalah putar layar dengan fitur screen rotate android kalian
3. Cobalah tekan tombol home saat aplikasi kalian dijalankan
4. Cobalah case case lain, dan perhatikan metode apa yang terpanggil


### PART 3. Menggunakan Debugger
Pada PART 3 ini kalian akan belajar untuk menggunakan debugger, yang nantinya akan berguna untuk menyusuri kode saat berjalan dan memeriksa nilai variabel

####1. Proyek kalkulator sederhana
Buatlah sebuah kalkulator dasar, kalian dapat menggunakan aplikasi kalkulator kalian sendiri apabila sudah ada.
Contoh :  
![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/3_1_P_images/pv_simplecalc.png) 

####2. Memasang break point di kode
Pada aplikasi kalian, cobalah taruh sebuah break point pada 1 baris kode.
Sebaiknya taruh break point tersebut pada kode assignment variabel.
Contoh seperti dibawah : 
![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/3_1_P_images/breakpoint.png) 

####3. Jalankan aplikasi, dan amati debugger
Jalankan aplikasi dari awal sampai kode yang telah kalian beri break point di jalankan.
Ketika telah sampai break point, aplikasi kalian mungkin akan freeze, dan akan muncul variabel variabel di aplikasi kalian. Coba cari variabel yang telah kalian breakpoint tadi, dan cek nilai dari variabel tersebut.
Contoh : 
![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/3_1_P_images/as-debugger.png)

