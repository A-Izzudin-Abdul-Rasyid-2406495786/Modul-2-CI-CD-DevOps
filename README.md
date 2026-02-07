# Reflection 1
- **Secure Coding**: 
Aku melakukan pengecekan null untuk menghindari `NullPointerException` dan memastikan ID produk selalu valid sebelum melakukan operasi hapus atau edit.
- **Clean Code:** 
Penamaan variabel dan metode yang jelas (`productData`, `delete`, `createProductPost`), metode yang memiliki satu tanggung jawab saja, serta kode yang mudah dibaca dan dimaintain
- **Proses try & error**  
  Saat membuat fitur baru, saya melakukan beberapa percobaan untuk memastikan tombol Edit dan Delete bekerja dengan baik.  
  Beberapa error muncul, seperti tombol Edit tidak mengarah ke halaman yang tepat dan routing Delete salah.  

- **Perbaikan masalah Edit**  
  Ternyata penyebab masalah ini terjadi adalah karena ketika createProduct berjalan, fungsi ini tidak membuat sebuah string id random untuk tiap productnya, sehingga aku menambahkan code untuk
  generate random id, agar ketika fungsi nya routing ke /product/list/{id}, id nya menjadi valid dan tidak berisi null. Sehingga bisa routing ke fungsi editproduct.

- **Perbaikan masalah Delete**  
  Mengubah redirect dari `"redirect:product/list"` menjadi `"redirect:/product/list"` dengan menambahkan garis miring di depannya.  
  Ternyata salah taruh "/" aja dapat menyebabkan masalah yang fatal.
  
# Reflection 2
## Unit test
- `How many unit tests should be made in a class?`
Menurutku tidak ada angka pasti dalam unit test, tapi selama itu mencakup semua skenario. Skenario positif, skenario negatif, skenario null, dll. maka akan menghasilkan code coverage yang semakin tinggi dan membuat kode kita lebih "kokoh" dan tidak rentan terhadap bug. Meskipun tidak menjamin akan 100% bebas dari bug.

- `How to make sure that our unit tests are enough to verify our program?`
Menurutku caranya adalah dengan memastikan code coverage setinggi mungkin dan Testing manual semua kemungkinannya, yaitu dengan cara akses langsung programnnya dan bruteforce semua kemungkinan input, skenario, dll. Untuk mencoba semua kemungkinan error.

- `If you have 100% code coverage, does
that mean your code has no bugs or errors?`
Tidak menjamin bahwa tidak ada error, kita tetap harus testing manual semua kemungkinan input, skenario, dll. secara bruteforce untuk mencari logika yang salah. Tapi code coverage membuat kita lebih yakin bahwa semua kodenya (yang telah tercoverage) telah teruji secara unit testing (programly testing).

- `What do you think about the cleanliness of the code of the new functional test suite? Will
the new code reduce the code quality?`
Pembuatan kelas baru dengan menyalin struktur yang sama akan membuat kode menjadi unclean. Hal ini secara langsung akan menurunkan kualitas kode secara keseluruhan karena menciptakan pengulangan yang tidak perlu.

  Pembuatan unit test baru dengan copy struktur yang sama akan menyebabkan duplikasi kode yang melanggar prinsip DRY (Don't Repeat Yourself) dan mempersulit maintanance di masa depan.

  Untuk memperbaikinya menurutku adalah prosedur setup dan variabel umum harus dikumpulkan ke dalam sebuah Base Class yang kemudian diwariskan ke setiap kelas tes spesifik. Ini akan membuat kode lebih bersih, mudah dibaca, dan lebih efisien untuk dikembangkan.
