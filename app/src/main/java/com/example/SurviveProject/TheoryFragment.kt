package com.example.SurviveProject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_theory.*

class TheoryFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var navController: NavController
    var readmodule = ""

    var listread: ArrayList<Int> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()
        navController = Navigation.findNavController(view)

        database.collection("Users").document(mAuth.uid.toString())
            .get().addOnSuccessListener {
                readmodule = it.get("module").toString()
                if (readmodule.contains("1")) {
                    listread.add(1)
                }
                if (readmodule.contains("2")) {
                    listread.add(2)
                }
                if (readmodule.contains("3")) {
                    listread.add(3)
                }
                if (readmodule.contains("4")) {
                    listread.add(4)
                }
                if (readmodule.contains("5")) {
                    listread.add(5)
                }
            }

        var course = requireActivity().intent.getIntExtra("Type", 0)
        if (course == 1) {
            backhomebtn.text = "next page"
            titlemodule.text = "Gempa Bumi"
            pictmodule.setImageResource(R.drawable.gempabumisquare)
            textmodule.text =
                "Gempa bumi adalah peristiwa berguncangnya bumi yang disebabkan oleh tumbukan antar lempeng bumi, aktivitas sesar (patahan), aktivitas gunungapi, atau runtuhan batuan.\n" +
                        "Jenis bencana ini bersifat merusak, dapat terjadi setiap saat " +
                        "dan berlangsung dalam waktu singkat. Gempa bumi dapat" +
                        "menghancurkan bangunan, jalan, jembatan, dan sebagainya dalam sekejap.\n\n" +
                        "Sampai saat ini, belum ada ahli dan institusi yang mampu memprediksi kapan terjadinya gempa bumi. Institusi yang berwenang untuk mengeluarkan informasi kejadian gempa bumi adalah BMKG."
            backhomebtn.setOnClickListener {
                backhomebtn.text = "next page"
                titlemodule.text = "Prabencana & saat bencana"
                pictmodule.setImageResource(R.drawable.gempabumisquare)
                textmodule.text =
                    "Prabencana\n\n" + "Melakukan latihan yang dapat bermanfaat dalam menghadapi reruntuhan saat gempa bumi, " +
                            "seperti merunduk, perlindungan terhadap kepala, berpegangan ataupun dengan bersembunyi di bawah meja. " + "Membangun konstruksi rumah yang tahan terhadap " +
                            "guncangan gempa bumi dengan fondasi yang kuat. Selain itu, Anda bisa merenovasi bagian bangunan yang sudah rentan.\n\n" +
                            "Saat Bencana\n\n" + "Guncangan akan terasa beberapa saat. Selama jangka waktu itu, upayakan keselamatan diri Anda dengan cara berlindung di bawah " +
                            "meja untuk menghindari dari benda-benda yang mungkin jatuh dan jendela kaca. Lindungi kepala dengan bantal atau helm, atau berdirilah " +
                            "di bawah pintu. Bila sudah terasa aman, segera lari keluar rumah." + "Bila keluar rumah, perhatikan kemungkinan pecahan kaca, genteng, atau material lain. Tetap lindungi kepala dan segera " +
                            "menuju ke lapangan terbuka, jangan berdiri dekat tiang, pohon, atau sumber listrik atau gedung yang mungkin roboh.\n" +
                            "Jangan gunakan lift apabila sudah terasa guncangan. Gunakan tangga darurat untuk evakuasi keluar bangunan. Apabila sudah di dalam elevator, tekan " +
                            "semua tombol atau gunakan interphone untuk panggilan kepada pengelola bangunan." +
                            "Saat terjadi gempa bumi besar, Anda akan kehilangan kontrol terhadap mobil."
                backhomebtn.setOnClickListener {
                    backhomebtn.text = "back to home"
                    titlemodule.text = "Pascabencana"
                    pictmodule.setImageResource(R.drawable.gempabumisquare)
                    textmodule.text = "Tetap waspada terhadap gempa bumi susulan. " +
                            "Anda dapat kembali ke rumah setelah keadaan dinyatakan aman dari pihak berwenang. " +
                            "Ketika berada di dalam bangunan, evakuasi diri Anda setelah gempa bumi berhenti. Perhatikan reruntuhan maupun benda-benda yang membahayakan pada saat evakuasi.\n\n" +
                            "Berdirilah di tempat terbuka jauh dari gedung dan instalasi listrik dan air. Apabila di luar bangunan dengan tebing di sekeliling, hindari daerah yang rawan longsor. " +
                            "Jika di dalam mobil, berhentilah tetapi tetap berada di dalam mobil. Hindari berhenti di bawah atau di atas jembatan atau rambu-rambu lalu lintas."
                    backhomebtn.setOnClickListener {
                        val intent = Intent(getActivity(), HomeActivity::class.java)
                        getActivity()?.startActivity(intent)
                        if (!readmodule.contains("1")) {
                            listread.add(1)
                            database.collection("Users").document(mAuth.uid.toString())
                                .update("module", listread)
                            database.collection("Users").document(mAuth.uid.toString())
                                .get().addOnSuccessListener {
                                    readmodule = it.get("module").toString()
                                }
                        }
                    }
                }
            }
        }
        if (course == 2) {
            backhomebtn.text = "next page"
            titlemodule.text = "Tsunami"
            pictmodule.setImageResource(R.drawable.tsunamisquare)
            textmodule.text = "Tsunami terdiri dari rangkaian gelombang laut yang mampu " +
                    "menjalar dengan kecepatan mencapai lebih dari 900 km/jam " +
                    "atau lebih.\n" + "Saat mencapai pantai yang dangkal, teluk, atau muara sungai, " +
                    "kecepatan gelombang tsunami akan menurun, namun ketinggian " +
                    "gelombang akan meningkat puluhan meter dan bersifat merusak.\n\n" + "Seperti gempa, belum ada ahli dan institusi yang mampu " +
                    "memprediksi dengan tepat kapan tsunami akan terjadi. Anda dapat" +
                    "mengenali suatu wilayah yang berpotensi terdampak tsunami dengan" +
                    "rambu peringatan bahaya tsunami."
            backhomebtn.setOnClickListener {
                backhomebtn.text = "next page"
                titlemodule.text = "Prabencana & saat bencana"
                pictmodule.setImageResource(R.drawable.tsunamisquare)
                textmodule.text =
                    "Prabencana\n\n" + "Ketahui tanda-tanda sebelum tsunami terjadi, terutama setelah" +
                            "gempa (intensitas gempa lama dan terasa kuat, air laut surut," +
                            "bunyi gemuruh dari tengah lautan, banyak ikan menggelepar di" +
                            "pantai yang airnya surut, dan tanda tanda alam lain).\n" +
                            "Segera menjauhi pantai dan tidak perlu melihat datangnya tsunami atau menangkap ikan yang terdampar di pantai karena air surut.\n\n" +
                            "Saat Bencana\n\n" + "Jika Anda berada di rumah, usahakan untuk tetap tenang dan segera membimbing keluarga untuk menyelamatkan diri ke " + "tempat yang lebih tinggi dan aman." +
                            "Tidak semua gempa memicu tsunami. Jika mendengar " + "sirine tanda bahaya atau pengumuman dari pihak " + "berwenang mengenai bahaya tsunami, Anda perlu segera " + "menyingkir dari daerah pantai.\nPerhatikan peringatan dan " +
                            "arahan dari pihak berwenang dalam proses evakuasi " + "Jika telah sampai di daerah tinggi, bertahanlah disana karena gelombang tsunami yang kedua dan ketiga biasanya lebih besar " +
                            "dari gelombang pertama serta dengarkan informasi dari pihak yang berwenang melalui radio atau alat komunikasi lainnya."
                backhomebtn.setOnClickListener {
                    backhomebtn.text = "back to home"
                    titlemodule.text = "Pascabencana"
                    pictmodule.setImageResource(R.drawable.tsunamisquare)
                    textmodule.text =
                        "Tetap utamakan keselamatan dan bukan barang-barang anda. Waspada dengan instalasi listrik dan pipa gas. " +
                                "Anda dapat kembali ke rumah setelah keadaan dinyatakan aman dari pihak berwenang. " + "Jauhi area yang tergenang dan rusak sampai ada informasi aman dari pihak berwenang. " +
                                "Hindari air yang menggenang karena kemungkinan kontaminasi zat-zat berbahaya dan ancaman tersengat aliran listrik " +
                                "Jauhi reruntuhan di dalam genangan air karena sangat berpengaruh terhadap keamanan perahu penyelamat dan orang-orang di sekitar."
                    backhomebtn.setOnClickListener {
                        val intent = Intent(getActivity(), HomeActivity::class.java)
                        getActivity()?.startActivity(intent)
                        if (!readmodule.contains("2")) {
                            listread.add(2)
                            database.collection("Users").document(mAuth.uid.toString())
                                .update("module", listread)
                            database.collection("Users").document(mAuth.uid.toString())
                                .get().addOnSuccessListener {
                                    readmodule = it.get("module").toString()
                                }
                        }
                    }
                }
            }
        }
        if (course == 3) {
            backhomebtn.text = "next page"
            titlemodule.text = "Gunungapi"
            pictmodule.setImageResource(R.drawable.gunungapisquare)
            textmodule.text =
                "Bahaya erupsi gunungapi memiliki dua jenis bahaya berdasarkan waktu kejadian, yaitu bahaya primer dan sekunder.\nBerikut ini bahaya dari erupsi gunungapi.\n\n" +
                        "1.Awan panas adalah aliran material vulkanik panas yang terdiri atas batuan berat, ringan (berongga) lava masif dan butiran klastik yang pergerakannya dipengaruhi " +
                        "gravitasi dan cenderung mengalir melalui lembah. Bahaya ini merupakan campuran material erupsi antara gas dan " +
                        "bebatuan (segala ukuran) yang terdorong ke bawah akibat densitas tinggi. Suhu material bisa mencapai 300 – 700°C, kecepatan awan panas lebih dari 70 km/jam.\n" +
                        "2.Aliran lava adalah magma yang meleleh ke permukaan bumi melalui rekahan, suhunya >10.000°C dan dapat merusak segala bentuk infrastruktur.\n" +
                        "3.Gas beracun adalah gas vulkanik yang dapat mematikan seketika apabila terhirup dalam tubuh. Gas tersebut antara lain CO2, SO2, Rn, H2S, HCl, HF, H2SO4. " +
                        "Gas tersebut biasanya tidak berwarna dan tidak berbau.\n" + "4.Lontaran material (pijar). Lontaran material terjadi ketika letusan magmatic berlangsung. Suhu mencapai " +
                        "200°C, diameter lebih dari 10 cm dengan daya lontar ratusan kilometer\n" +
                        "5.Hujan abu. Material abu tampak halus dan bergerak sesuai arah angin\n" +
                        "6.Lahar Letusan, lahar letusan terjadi pada gunung berapi yang mempunyai danau kawah, terjadi bersamaan saat letusan. Air bercampur material lepas gunung berapi " +
                        "mengalir dan bentuk banjir lahar."
            backhomebtn.setOnClickListener {
                backhomebtn.text = "next page"
                titlemodule.text = "Prabencana & saat bencana"
                pictmodule.setImageResource(R.drawable.gunungapisquare)
                textmodule.text =
                    "Prabencana\n\n" + "Siapkan masker dan kacamata pelindung untuk mengatasi debu vulkanik. " + "Siapkan dukungan logistik, antara lain makanan siap saji, " +
                            "lampu senter dan baterai cadangan, uang tunai yang cukup serta obat-obatan khusus sesuai pemakai\n\n" +
                            "Saat Bencana\n\n" + "Tidak berada di lokasi yang direkomendasikan untuk dikosongkan " + "Hindari tempat terbuka. Lindungi diri dari abu letusan gunungapi. " + "Jangan memakai lensa kontak. " +
                            "Gunakan kacamata pelindung " + "Gunakan masker atau kain basah untuk menutup mulut dan hidung " +
                            "Kenakan pakaian tertutup yang melindungi tubuh seperti, baju lengan panjang, celana panjang, dan topi."
                backhomebtn.setOnClickListener {
                    backhomebtn.text = "back to home"
                    titlemodule.text = "Pascabencana"
                    pictmodule.setImageResource(R.drawable.gunungapisquare)
                    textmodule.text =
                        "Kurangi terpapar dari abu vulkanik. " + "Hindari mengendarai mobil di daerah yang terkena hujan abu vulkanik sebab bisa merusak mesin kendaraan.\n\n" +
                                "Bersihkan atap dari timbunan debu vulkanik karena beratnya bisa merobohkan dan merusak atap rumah atau bangunan.\n" + "Waspadai wilayah aliran sungai yang berpotensi terlanda " +
                                "bahaya lahar pada musim hujan. "
                    backhomebtn.setOnClickListener {
                        val intent = Intent(getActivity(), HomeActivity::class.java)
                        getActivity()?.startActivity(intent)
                        if (!readmodule.contains("3")) {
                            listread.add(3)
                            database.collection("Users").document(mAuth.uid.toString())
                                .update("module", listread)
                            database.collection("Users").document(mAuth.uid.toString())
                                .get().addOnSuccessListener {
                                    readmodule = it.get("module").toString()
                                }
                        }
                    }
                }
            }
        }
        if (course == 4) {
            backhomebtn.text = "next page"
            titlemodule.text = "Banjir"
            pictmodule.setImageResource(R.drawable.banjirsquare)
            textmodule.text =
                "Banjir merupakan peristiwa ketika air menggenangi suatu wilayah yang biasanya tidak digenangi air dalam jangka " +
                        "waktu tertentu. Banjir biasanya terjadi karena curah hujan turun terus menerus dan mengakibatkan meluapnya air sungai, danau, " +
                        "laut atau drainase karena jumlah air yang melebihi daya tampung media penopang air dari curah hujan tadi.\n\n" +
                        "Selain disebabkan faktor alami, yaitu curah hujan yang tinggi, banjir juga terjadi karena ulah manusia. Contoh, berkurangnya kawasan " +
                        "resapan air karena alih fungsi lahan, penggundulan hutan yang meningkatkan erosi dan mendangkalkan sungai, serta perilaku tidak bertanggung jawab seperti membuang sampah di sungai dan " +
                        "mendirikan hunian di bantaran sungai"
            backhomebtn.setOnClickListener {
                backhomebtn.text = "next page"
                titlemodule.text = "Prabencana & saat bencana"
                pictmodule.setImageResource(R.drawable.banjirsquare)
                textmodule.text =
                    "Prabencana\n\n" + "Membicarakan dengan anggota keluarga mengenai ancaman banjir dan merencanakan tempat pertemuan apabila " +
                            "anggota keluarga terpencar-pencar " + "Membuat persiapan untuk hidup mandiri selama sekurangnya tiga hari, misalnya persiapan tas siaga bencana, penyediaan " +
                            "makanan dan air minum " + "Berkaitan dengan harta dan kepemilikan, maka Anda bisa membuat catatan harta kita, mendokumentasikannya dalam " +
                            "foto, dan simpan dokumen tersebut di tempat yang aman. " + "Hindari membangun di tempat rawan banjir kecuali ada " +
                            "upaya penguatan dan peninggian bangunan rumah.\n\n" +
                            "Saat Bencana\n\n" + "Apabila banjir akan terjadi di wilayah Anda, maka simaklah informasi dari berbagai media mengenai informasi banjir untuk " +
                            "meningkatkan kesiapsiagaan. " + "Ketahui risiko banjir dan banjir bandang di tempat Anda, misalnya banjir bandang dapat terjadi di tempat Anda dengan " +
                            "atau tanpa peringatan pada saat hujan biasa atau deras.\n" + "Matikan semua jaringan listrik apabila ada instruksi dari pihak " +
                            "berwenang. Cabut alat-alat yang masih tersambung dengan listrik. Jangan menyentuh peralatan yang bermuatan listrik " +
                            "apabila Anda berdiri di atas/dalam air. " + "Jika ada perintah evakuasi dan Anda harus meninggalkan rumah Jangan berjalan di arus air. Beberapa langkah berjalan " +
                            "di arus air dapat mengakibatkan Anda jatuh."
                backhomebtn.setOnClickListener {
                    backhomebtn.text = "back to home"
                    titlemodule.text = "Pascabencana"
                    pictmodule.setImageResource(R.drawable.banjirsquare)
                    textmodule.text =
                        "Hindari air banjir karena kemungkinan kontaminasi zat-zat berbahaya dan ancaman kesetrum.\n\n" + "Hindari area yang airnya baru saja surut karena jalan bisa " +
                                "saja keropos dan ambles. " + "Hindari lokasi yang masih terkena bencana, kecuali jika pihak yang berwenang membutuhkan sukarelawan " +
                                "Hati-hati saat memasuki gedung karena ancaman kerusakan " + "yang tidak terlihat seperti pada fondasi."
                    backhomebtn.setOnClickListener {
                        val intent = Intent(getActivity(), HomeActivity::class.java)
                        getActivity()?.startActivity(intent)
                        if (!readmodule.contains("4")) {
                            listread.add(4)
                            database.collection("Users").document(mAuth.uid.toString())
                                .update("module", listread)
                            database.collection("Users").document(mAuth.uid.toString())
                                .get().addOnSuccessListener {
                                    readmodule = it.get("module").toString()
                                }
                        }
                    }
                }
            }
        }
        if (course == 5) {
            backhomebtn.text = "next page"
            titlemodule.text = "Tanah Longsor"
            pictmodule.setImageResource(R.drawable.banjirsquare)
            textmodule.text = "Bencana tanah longsor seringkali dipicu karena kombinasi dari curah hujan yang tinggi, lereng terjal, tanah yang kurang " +
                              "padat serta tebal, terjadinya pengikisan, berkurangnya tutupan vegetasi, dan getaran.\n\n" + "Bencana longsor biasanya terjadi begitu cepat sehingga " +
                              "menyebabkan terbatasnya waktu untuk melakukan evakuasi mandiri. Material longsor menimbun apa saja yang berada di jalur longsoran."
            backhomebtn.setOnClickListener {
                backhomebtn.text = "next page"
                titlemodule.text = "Prabencana & saat bencana"
                pictmodule.setImageResource(R.drawable.banjirsquare)
                textmodule.text = "Prabencana\n\n" + "Mengurangi tingkat keterjalan lereng permukaan maupun air " +
                            "tanah. (Perhatikan fungsi drainase adalah untuk menjauhkan air dari lereng, menghindari air meresap ke dalam lereng atau " +
                            "menguras air ke dalam lereng ke luar lereng. Jadi drainase harus dijaga agar jangan sampai tersumbat atau meresapkan " +
                            "air ke dalam tanah)." + "Terasering dengan sistem drainase yang tepat (drainase pada " +
                            "teras - teras dijaga jangan sampai menjadi jalan meresapkan " +
                            "air ke dalam tanah).\n" + "Pondasi tiang pancang sangat disarankan untuk menghindari bahaya liquefaction (infeksi cairan).\n" + "Dalam beberapa kasus relokasi sangat disarankan. " +
                            "Menanami kawasan yang gersang dengan tanaman yang memiliki akar" +
                            "kuat, banyak dan dalam seperti nangka, durian, pete, kaliandra dan sebagainya. " + "Tidak mendirikan bangunan permanen di daerah tebing dan " +
                            "tanah yang tidak stabil (tanah gerak).\n\n" +
                            "Saat Bencana\n\n" + "Segera evakuasi untuk menjauhi suara gemuruh atau arah datangnya longsoran. " + "Apabila mendengar suara sirine peringatan longsor, " +
                            "segera evakuasi ke arah zona evakuasi yang telah ditentukan.\nBeberapa wilayah di Indonesia telah terpasang Sistem Peringatan Dini Longsor."
                backhomebtn.setOnClickListener {
                    backhomebtn.text = "back to home"
                    titlemodule.text = "Pascabencana"
                    pictmodule.setImageResource(R.drawable.banjirsquare)
                    textmodule.text = "Hindari wilayah longsor karena kondisi tanah yang labil " + "Apabila hujan turun setelah longsor terjadi, antisipasi " +
                            "longsor susulan. " + "Penghijauan dengan tanaman yang sistem perakarannya dalam dan jarak tanam yang tepat (khusus untuk lereng curam, " +
                            "dengan kemiringan lebih dari 40 derajat atau sekitar 80% sebaiknya tanaman tidak terlalu rapat serta diseling-selingi " +
                            "dengan tanaman yang lebih pendek dan ringan, di bagian dasar ditanam rumput). " + "Hindarkan daerah rawan bencana untuk pembangunan " +
                            "pemukiman dan fasilitas utama lainnya."
                    backhomebtn.setOnClickListener {
                        val intent = Intent(getActivity(), HomeActivity::class.java)
                        getActivity()?.startActivity(intent)
                        if (!readmodule.contains("5")) {
                            listread.add(5)
                            database.collection("Users").document(mAuth.uid.toString())
                                .update("module", listread)
                            database.collection("Users").document(mAuth.uid.toString())
                                .get().addOnSuccessListener {
                                    readmodule = it.get("module").toString()
                                }
                        }
                    }
                }
            }
        }
    }
}