# Modul 3 : Widget

### Kalian akan belajar
Dalam Focus Group kali ini kalian akan mempelajari : 
1. Menambahkan elemen Spinner untuk memilih satu item dari serangkaian item.
2. Menampilkan Dialog untuk memberikan feedback kepada pengguna dengan custom design.
3. Mengimplementasikan Cardview kedalam RecyclerView.`

### PART 1 : Menerapkan Spinner
Dalam praktik ini kalian akan mempelajari cara menerapkan spinner widget kedalam sebuah layout.

#### 1. Membuat Layout dengan Spinner
Buatlah sebuah aktivitas dengan layout seperti berikut

![alt text](https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/images/4_1_P_images/dg_phonenumberspinner_layout_annotated.png)

Pada Gambar diatas kalian akan membuat layout dengan 2 bagian yaitu : 
1. untuk memasukkan informasi nomor telepon
2. untuk menampilkan nomor telepon

Tambahkan kodingan berikut untuk membuat spinner, dan letakkan diantara EditText dan Tombol "Show" :
```xml
    . . . 
    <Spinner
       android:id="@+id/label_spinner"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content">
    </Spinner>
    . . . 
```
#### 2. Tambahkan Kode untuk mengaktifkan spinner dan listener-nya
1. Buka **strings.xml** untuk mendefinisikan nilai yang bisa dipilih (Home, Work, Mobile, dan Other) untuk spinner sebagai larik string ``labels_array``.
```xml
<string-array name="labels_array">
        <item>Home</item>
        <item>Work</item>
        <item>Mobile</item>
        <item>Other</item>
</string-array>
```
2. Untuk mendefinisikan callback pilihan untuk spinner, ubah kelas MainActivity Anda untuk mengimplementasikan antarmuka AdapterView.OnItemSelectedListener seperti yang ditampilkan:
```java
public class MainActivity extends AppCompatActivity implements
            AdapterView.OnItemSelectedListener {
```
Selagi Anda mengetik AdapterView. di pernyataan di atas, Android Studio otomatis mengimpor widget AdapterView.
3. Klik bola lampu dan pilih Implement methods. Metode onItemSelected() dan onNothingSelected() yang diperlukan untuk OnItemSelectedListener, seharusnya sudah disorot, dan opsi "Insert @Override" harus dicentang. Klik OK.
4. Buat instance objek spinner di metode onCreate() menggunakan elemen Spinner di layout (label_spinner), dan tetapkan listener-nya (spinner.setOnItemSelectedListener) di metode onCreate(). Tambahkan kode ke metode onCreate():
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
   ...
   // Create the spinner.
   Spinner spinner = (Spinner) findViewById(R.id.label_spinner);
   if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
   }
   ...
```
5. Lanjutkan mengedit metode onCreate(), tambahkan pernyataan yang membuat ArrayAdapter dengan larik string (labels_array) menggunakan layout spinner sederhana yang disediakan oleh Android untuk setiap item (layout.simple_spinner_item):
```java
...
// Create ArrayAdapter using the string array and default spinner layout.
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.labels_array, android.R.layout.simple_spinner_item);
...
```
6. Tetapkan layout untuk pilihan spinner menjadi simple_spinner_dropdown_item, lalu terapkan adaptor ke spinner:
```java
   ...
   // Specify the layout to use when the list of choices appears.
   adapter.setDropDownViewResource
                       (android.R.layout.simple_spinner_dropdown_item);
   // Apply the adapter to the spinner.
   if (spinner != null) {
            spinner.setAdapter(adapter);
   }
   ...
```

#### 3. Tambahkan kode untuk merespons pilihan pengguna
1. Deklarasikan string mSpinnerLabel di awal definisi kelas MainActivity:
```java
public class MainActivity extends AppCompatActivity implements
               AdapterView.OnItemSelectedListener {
 private String mSpinnerLabel = "";
 ...
}
```
2. Tambahkan kode ke metode callback onItemSelected() kosong, seperti yang ditampilkan di bawah ini, untuk mengambil item yang dipilih pengguna menggunakan getItemAtPosition, dan tetapkan ke mSpinnerLabel.
```java
public void onItemSelected(AdapterView<?> adapterView, View view, int
               i, long l) {
   mSpinnerLabel = adapterView.getItemAtPosition(i).toString();
   showText(view);
}
```
3. Tambahkan kode ke metode callback onNothingSelected() yang kosong, seperti yang ditampilkan di bawah ini, untuk menampilkan pesan logcat jika tidak satu pun yang dipilih:
```java
public void onNothingSelected(AdapterView<?> adapterView) {
    Log.d(TAG, "onNothingSelected: ");
}
```
