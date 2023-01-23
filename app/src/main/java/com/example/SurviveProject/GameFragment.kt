package com.example.SurviveProject

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_game.*


class GameFragment : Fragment() {

    private lateinit var timer: CountDownTimer
    private lateinit var mAuth: FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var database: FirebaseFirestore
    var quiztype = 0
    var questionnumber = 0
    var currentscore = 0
    var levellist = ""
    var jumlahscore = 0
    var totalgold = 0
    var trueanswer = ""
    var achieve = ""
    var lvl1score = 0
    var lvl2score = 0
    var lvl3score = 0
    var lvl4score = 0
    var lvl5score = 0
    var readmodule = ""
    var listread: ArrayList<String> = ArrayList()
    var listAchieve: ArrayList<Int> = ArrayList()
    var listData: ArrayList<gameplayquiz> = ArrayList()
    var listLevel: ArrayList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)
        database = FirebaseFirestore.getInstance()

            database.collection("Users").document(mAuth.uid.toString())
                .get().addOnSuccessListener {
                levellist = it.get("level").toString()
                totalgold = it.getLong("gold")!!.toInt()
                jumlahscore = it.getLong("score")!!.toInt()
                achieve = it.get("achievementList").toString()
                readmodule = it.get("module").toString()
                lvl1score = it.getLong("level1score")!!.toInt()
                lvl2score = it.getLong("level2score")!!.toInt()
                lvl3score = it.getLong("level3score")!!.toInt()
                lvl4score = it.getLong("level4score")!!.toInt()
                lvl5score = it.getLong("level5score")!!.toInt()

                if (readmodule.contains("1")){
                    listread.add("1")
                }
                if (readmodule.contains("2")){
                    listread.add("2")
                }
                if (readmodule.contains("3")){
                    listread.add("3")
                }
                if (readmodule.contains("4")){
                    listread.add("4")
                }
                if (readmodule.contains("5")){
                    listread.add("5")
                }


                if (achieve.contains("1")) {
                    listAchieve.add(1)
                }
                if (achieve.contains("2")) {
                    listAchieve.add(2)
                }
                if (achieve.contains("3")) {
                    listAchieve.add(3)
                }
                if (achieve.contains("4")) {
                    listAchieve.add(4)
                }
                if (achieve.contains("5")) {
                    listAchieve.add(5)
                }
                if (achieve.contains("6")) {
                    listAchieve.add(6)
                }
                if (achieve.contains("7")) {
                    listAchieve.add(7)
                }
            }
        quiztype = requireActivity().intent.getIntExtra("Status", 0)
        timer = object : CountDownTimer(50000, 1000) {
            override fun onTick(p0: Long) {
                timernumber.text = (p0 / 1000).toString()
            }

            override fun onFinish() {
                database.collection("Users").document(mAuth.uid.toString()).update(
                    "score", currentscore + jumlahscore, "gold",
                    currentscore + totalgold, "updatescore", currentscore
                )

                layoutjawabana.isClickable = false
                layoutjawabanb.isClickable = false
                layoutjawabanc.isClickable = false
                layoutjawaband.isClickable = false

                Handler().postDelayed({
                    navController.navigate(R.id.action_gameFragment_to_postgameFragment)
                }, 500)
            }

        }
        if (quiztype == 1) {
            listData.add(
                gameplayquiz(
                    "Salah satu yang bukan penyebab gempa bumi adalah",
                    "Curah Hujan Tinggi",
                    "Aktivitas Gunung Api",
                    "Tumbukan antara lempeng bumi",
                    "Aktivitas Sesar (Patahan)",
                    "Curah Hujan Tinggi"
                )
            )
            listData.add(
                gameplayquiz(
                    "Institusi yang berwenang untuk mengeluarkan informasi kejadian gempa bumi adalah",
                    "BMKG",
                    "BMKG",
                    "BNPB",
                    "BATAN",
                    "BPK"
                )
            )
            listData.add(
                gameplayquiz(
                    "Apa yang dilakukan saat terjadi gempa bumi di dalam rumah",
                    "Bersembunyi di bawah meja",
                    "Bersembunyi di bawah meja",
                    "Memindahkan barang berharga",
                    "Memanggil pemadam kebakaran",
                    "Menggunakan kacamata pelindung"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang harus dihindari ketika terjadi gempabumi adalah",
                    "Menggunakan lift",
                    "Berlindung di bawah meja",
                    "Melindungi kepala dengan bantal atau helm",
                    "Menuju lapangan terbuka",
                    "Menggunakan lift"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang mungkin dapat terjadi setelah terjadinya gempabumi adalah",
                    "Gempabumi susulan",
                    "Curah hujan tinggi",
                    "Suhu ruangan meningkat",
                    "Gempabumi susulan",
                    "Gelombang laut rendah"
                )
            )
            listData.add(
                gameplayquiz(
                    "Peristiwa berguncangnya bumi yang disebabkan oleh tumbukan antar lempeng bumi adalah",
                    "Gempa Bumi",
                    "Gempa Bumi",
                    "Kebakaran",
                    "Banjir",
                    "Kekeringan"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang terjadi ketika terjadi gempabumi saat di dalam mobil adalah",
                    "Kehilangan kontrol terhadap mobil",
                    "Kecepatan mobil bertambah",
                    "Mobil akan berhenti",
                    "Kehilangan kontrol terhadap mobil",
                    "Kecepatan mobil berkurang"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu bentuk kesiapan terhadap terjadinya bencana alam gempa bumi adalah",
                    "Membangun konstruksi rumah yang tahan terhadap guncangan gempa bumi",
                    "Membangun konstruksi rumah yang tahan terhadap guncangan gempa bumi",
                    "Membuang sampah pada tempatnya",
                    "Menanam pohon",
                    "Menyiapkan masker dan kacamata pelindung"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu yang bukan bentuk dari kesiapan terhadap terjadinya bencana alam gempabumi adalah",
                    "Membiarkan bagian bangunan yang sudah rentan",
                    "Membiarkan bagian bangunan yang sudah rentan",
                    "Memperhatikan daerah rawan gempa bumi",
                    "Menyiapkan rencana untuk penyelamatan diri",
                    "Melakukan Latihan rute  evakuasi gempabumi"
                )
            )
            listData.add(
                gameplayquiz(
                    "Alat yang dapat digunakan untuk melindungi kepala saat terjadi gempa bumi",
                    "Helm",
                    "Helm",
                    "Kunci ",
                    "Kertas",
                    "Gunting"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu hal yang dilakukan pascabencana gempa bumi adalah",
                    "Berdiri di tempat terbuka dan jauhi tiang listrik",
                    "Mendekati pantai",
                    "Berdiri di tempat terbuka dan jauhi tiang listrik",
                    "Menuju tempat tinggi",
                    "Memadamkan api"
                )
            )

            timer.start()
            nextq()
            nomorsoal.text = "1/10"

            layoutjawabana.setOnClickListener {
                if (trueanswer == "A") {
                    currentscore = 50 + currentscore
                    layoutjawabana.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabana.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanb.setOnClickListener {
                if (trueanswer == "B") {
                    currentscore = 50 + currentscore
                    layoutjawabanb.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanb.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanc.setOnClickListener {
                if (trueanswer == "C") {
                    currentscore = 50 + currentscore
                    layoutjawabanc.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanc.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawaband.setOnClickListener {
                if (trueanswer == "D") {
                    currentscore = 50 + currentscore
                    layoutjawaband.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawaband.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
        }
        if (quiztype == 2) {
            listData.add(
                gameplayquiz(
                    "Salah satu tanda sebelum terjadinya tsunami adalah",
                    "Air laut surut",
                    "Curah Hujan Tinggi",
                    "Air laut surut",
                    "Langit menjadi gelap",
                    "Terdapat asap"
                )
            )
            listData.add(
                gameplayquiz(
                    "Kecepatan gelombang tsunami adalah",
                    "Lebih dari 900 Km/Jam",
                    "10 Km/Jam",
                    "20 Km/Jam",
                    "500 Km/Jam",
                    "Lebih dari 900 Km/Jam"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu hal yang harus dilakukan ketika terjadi tsunami ",
                    "Menuju tempat yang lebih tinggi",
                    "Berlindung dibawah meja",
                    "Memindahkan barang berharga",
                    "Menuju tempat yang lebih tinggi",
                    "Menggunakan kacamata pelindung"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang dihindari ketika terjadi tsunami",
                    "Mendekati pantai",
                    "Mendekati pantai",
                    "Menuju tempat tinggi",
                    "Mencari bantuan",
                    "Bertahan di tempat yang lebih aman"
                )
            )
            listData.add(
                gameplayquiz(
                    "Apa yang harus dilakukan ketika terjadi tsunami saat sedang berada di kapal atau perahu yang berlayar",
                    "Tetap berlayar dan menghindari wilayah Pelabuhan",
                    "Mencari ikan",
                    "Memberhentikan kapal",
                    "Menggunakan kaca pelindung",
                    "Tetap berlayar dan menghindari wilayah Pelabuhan"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu cara untuk mengetahui suatu wilayah yang berdampak tsunami adalah",
                    "Mengenali rambu peringatan bahaya tsunami",
                    "Mencari mata angin",
                    "Menghitung suhu ruangan",
                    "Mengenali rambu peringatan bahaya tsunami",
                    "Menentukan rute perjalanan"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu bentuk kesiapan terhadap terjadinya bencana alam tsunami ",
                    "Mengetahui tanda tanda sebelum terjadinya tsunami",
                    "Mengetahui tanda tanda sebelum terjadinya tsunami",
                    "Memperbaiki bangunan yang sudah rentan",
                    "Tidak menggunakan lensa kontak",
                    "Menggunakan pakaian yang tertutup untuk melindungi tubuh"
                )
            )
            listData.add(
                gameplayquiz(
                    "Apa yang harus dilakukan ketika sedang melakukan evakuasi dengan kendaraan dan terjadi kemacetan",
                    "Melanjutkan evakuasi dengan berjalan kaki",
                    "Melanjutkan evakuasi dengan berjalan kaki",
                    "Menunggu di dalam mobil",
                    "Menambah kecepatan mobil",
                    "Mengurangi kecepatan mobil"
                )
            )
            listData.add(
                gameplayquiz(
                    "Jika telah sampai di area tinggi apa yang harus dilakukan",
                    "Bertahan di area tersebut",
                    "Bertahan di area tersebut",
                    "Meninggalkan area tersebut",
                    "Berlari menjauhi area tinggi",
                    "Menggunakan masker dan kacamata pelindung"
                )
            )
            listData.add(
                gameplayquiz(
                    "Apa yang terjadi ketika terdapat gelombang tsunami susulan",
                    "Gelombang semakin tinggi dan berbahaya",
                    "Gelombang menjadi tidak berbahaya",
                    "Gelombang semakin tinggi dan berbahaya",
                    "Gelombang hilang",
                    "Gelombang semakin rendah"
                )
            )
            listData.add(
                gameplayquiz(
                    "Apa yang harus dilakukan jika terjadi bencana alam tsunami saat berada di rumah",
                    "Menyelamatkan diri ke tempat yang lebih tinggi",
                    "Menyelamatkan diri ke tempat yang lebih tinggi",
                    "Mendekati pantai",
                    "Menangkap ikan yang terdampar",
                    "Menggunakan kacamata pelindung"
                )
            )

            timer.start()
            nextq()
            nomorsoal.text = "1/10"

            layoutjawabana.setOnClickListener {
                if (trueanswer == "A") {
                    currentscore = 50 + currentscore
                    layoutjawabana.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabana.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanb.setOnClickListener {
                if (trueanswer == "B") {
                    currentscore = 50 + currentscore
                    layoutjawabanb.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanb.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanc.setOnClickListener {
                if (trueanswer == "C") {
                    currentscore = 50 + currentscore
                    layoutjawabanc.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanc.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawaband.setOnClickListener {
                if (trueanswer == "D") {
                    currentscore = 50 + currentscore
                    layoutjawaband.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawaband.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
        }
        if (quiztype == 3) {
            listData.add(
                gameplayquiz(
                    "Dua jenis bahaya berdasarkan waktu terjadi erupsi gunungapi",
                    "Primer dan sekunder",
                    "Primer dan sekunder",
                    "Primer dan tersier",
                    "Tersier",
                    "Sekunder dan tersier"
                )
            )
            listData.add(
                gameplayquiz(
                    "Aliran material vulkanik panas yang terdiri atas batuan berat, ringan (berongga) lava masif adalah",
                    "Awan panas",
                    "Gas beracun",
                    "Awan panas",
                    "Hujan abu",
                    "Aliran lava"
                )
            )
            listData.add(
                gameplayquiz(
                    "Magma yang meleleh ke permukaan bumi melalui rekahan disebut",
                    "Aliran lava",
                    "Aliran lava",
                    "Runtuhan batu",
                    "Lontaran material (pijar)",
                    "Awan panas"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang harus dihindari ketika terjadi bencana alam erupsi adalah",
                    "Menggunakan lensa kontak",
                    "Menggunakan masker dan kacamata pelindung",
                    "Menggunakan pakaian tertutup",
                    "Menuju ke tempat evakuasi",
                    "Menggunakan lensa kontak"
                )
            )
            listData.add(
                gameplayquiz(
                    "Berapa jumlah tingkatan status (level) ancaman bahaya erupsi gunungapi",
                    "4",
                    "5",
                    "8",
                    "4",
                    "2"
                )
            )
            listData.add(
                gameplayquiz(
                    "Material abu yang berbentuk halus dan bergerak sesuai arah angin adalah",
                    "Hujan abu",
                    "Gas beracun",
                    "Awan panas",
                    "Hujan abu",
                    "Magma"
                )
            )
            listData.add(
                gameplayquiz(
                    "Kecepatan awan panas adalah",
                    "70 Km/jam",
                    "10 Km/Jam",
                    "2 m/Jam",
                    "70 cm/Jam",
                    "70 Km/jam"
                )
            )
            listData.add(
                gameplayquiz(
                    "Peningkatan kegiatan berupa kelainan yang tampak secara visual atau hasil pemeriksaan kawah merupakan status ancaman erupsi tingkat",
                    "2",
                    "1",
                    "2",
                    "3",
                    "4"
                )
            )
            listData.add(
                gameplayquiz(
                    "Tingkatan yang menunjukkan jelang letusan utama, letusan awal terjadi berupa abu atau asap merupakan status ancaman erupsi tingkat",
                    "4",
                    "2",
                    "3",
                    "1",
                    "4"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang harus dilakukan pasca terjadinya bencana erupsi gunung api adalah",
                    "Mengurangi terpapar abu vulkanik",
                    "Mengurangi terpapar abu vulkanik",
                    "Mengendarai mobil di daerah yang terkena hujan abu vulkanik",
                    "Membiarkan debu vulkanik yang terdapat pada atap rumah",
                    "Berenang di aliran sungai yang terkena lahar"
                )
            )
            listData.add(
                gameplayquiz(
                    "Gas vulkanik yang dapat mematikan seketika apabila terhirup dalam tubuh adalah",
                    "Gas beracun",
                    "Aliran lava",
                    "Gas beracun",
                    "Magma",
                    "Hujan abu"
                )
            )

            timer.start()
            nextq()
            nomorsoal.text = "1/10"

            layoutjawabana.setOnClickListener {
                if (trueanswer == "A") {
                    currentscore = 50 + currentscore
                    layoutjawabana.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabana.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanb.setOnClickListener {
                if (trueanswer == "B") {
                    currentscore = 50 + currentscore
                    layoutjawabanb.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanb.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanc.setOnClickListener {
                if (trueanswer == "C") {
                    currentscore = 50 + currentscore
                    layoutjawabanc.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanc.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawaband.setOnClickListener {
                if (trueanswer == "D") {
                    currentscore = 50 + currentscore
                    layoutjawaband.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawaband.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
        }
        if (quiztype == 4) {
            listData.add(
                gameplayquiz(
                    "Peristiwa ketika air menggenangi suatu wilayah yang biasanya tidak digenangi air dalam jangka waktu tertentu disebut",
                    "Banjir",
                    "Gempabumi",
                    "Puting beliung",
                    "Banjir",
                    "Tanah longsor"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu bentuk pencegahan banjir adalah",
                    "Membuang sampah pada tempatnya",
                    "Membuang sampah pada tempatnya",
                    "Penebangan hutan secara liar",
                    "Memperkecil drainase",
                    "Mendangkalkan sungai"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu faktor penyebab banjir adalah",
                    "Curah hujan tinggi",
                    "Curah hujan tinggi",
                    "Aktivitas sesar (Patahan)",
                    "Erupsi ",
                    "Tumbukan antara lempeng bumi"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu bentuk kesiapan terhadap bencana banjir adalah",
                    "Melakukan persiapan untuk rute evakuasi",
                    "Melakukan persiapan untuk rute evakuasi",
                    "Membaca ramalan cuaca",
                    "Mengenali rambu lalu lintas",
                    "Mempersiapkan masker"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang dihindari saat terjadi bencana banjir",
                    "Mengendarai mobil di area banjir",
                    "Mengendarai mobil di area banjir",
                    "Mengikuti perintah evakuasi",
                    "Mematikan semua jaringan listrik",
                    "Membersihkan dan menyiapkan penampungan air"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang dilakukan pascabencana banjir adalah",
                    "Menghindari air yang bergerak",
                    "Menghindari air yang bergerak",
                    "Kembali kerumah sesuai dengan keinginan masing masing",
                    "Berada di dalam rumah yang masih dikelilingi air",
                    "Berjalan melalui air banjir"
                )
            )
            listData.add(
                gameplayquiz(
                    "Perilaku manusia yang dapat menjadi penyebab banjir adalah",
                    "Membuang sampah di sungai",
                    "Membuang sampah di sungai",
                    "Melakukan penanaman pohon",
                    "Curah hujan tinggi",
                    "Menggunakan sepeda saat berpergian"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang dilakukan saat bersiap untuk melakukan evakuasi adalah",
                    "Menyimpan barang berharga di tempat yang lebih tinggi",
                    "Menyimpan barang berharga di tempat yang lebih tinggi",
                    "Menyalakan aliran listrik",
                    "Mengemudikan mobil",
                    "Membiarkan alat alat yang masih tersambung dengan listrik"
                )
            )
            listData.add(
                gameplayquiz(
                    "Bentuk persiapan sebelum terjadinya bencana banjir adalah",
                    "Mengetahui saluran dan jalur yang sering dilalui air banjir dan apa dampaknya untuk rumah kita",
                    "Mengetahui saluran dan jalur yang sering dilalui air banjir dan apa dampaknya untuk rumah kita",
                    "Menyiapkan masker dan kacamata pelindung",
                    "Berlindung di bawah meja",
                    "Menutup drainase "
                )
            )
            listData.add(
                gameplayquiz(
                    "Berkurangnya kawasan resapan air karena alih fungsi lahan dapat menyebabkan",
                    "Banjir",
                    "Tsunami",
                    "Banjir",
                    "Erupsi gunungapi",
                    "Gempa Bumi"
                )
            )
            listData.add(
                gameplayquiz(
                    "Bencana alam yang salah satu nya disebabkan oleh mendirikan hunian di bantaran sungai adalah",
                    "Banjir",
                    "Tsunami",
                    "Banjir",
                    "Erupsi gunungapi",
                    "Gempa Bumi"
                )
            )

            timer.start()
            nextq()
            nomorsoal.text = "1/10"

            layoutjawabana.setOnClickListener {
                if (trueanswer == "A") {
                    currentscore = 50 + currentscore
                    layoutjawabana.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabana.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanb.setOnClickListener {
                if (trueanswer == "B") {
                    currentscore = 50 + currentscore
                    layoutjawabanb.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanb.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanc.setOnClickListener {
                if (trueanswer == "C") {
                    currentscore = 50 + currentscore
                    layoutjawabanc.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanc.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawaband.setOnClickListener {
                if (trueanswer == "D") {
                    currentscore = 50 + currentscore
                    layoutjawaband.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawaband.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }

        }
        if (quiztype == 5) {
            listData.add(
                gameplayquiz(
                    "Bencana yang dipicu karena kombinasi dari curah hujan yang tinggi, lereng terjal, tanah yang kurang padat serta tebal, terjadinya pengikisan, berkurangnya tutupan vegetasi, dan getaran adalah",
                    "Tanah Longsor",
                    "Erupsi Gunungapi",
                    "Tsunami",
                    "Gempabumi",
                    "Tanah longsor"
                )
            )
            listData.add(
                gameplayquiz(
                    "Sistem drainase yang dapat mengurangi potensi terjadinya potensi tanah longsor disebut",
                    "Terasering",
                    "Terasering",
                    "Reboisasi",
                    "Erosi",
                    "Asimilasi"
                )
            )
            listData.add(
                gameplayquiz(
                    "Peristiwa bergesernya massa tanah dan batuan pada bidang gelincir berbentuk rata disebut",
                    "Longsoran translasi",
                    "Longsoran rotasi",
                    "Longsoran translasi",
                    "Pergerakan blok",
                    "Pergerakan tanah"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu bentuk pencegahan terjadinya bencana alam tanah longsor adalah",
                    "Membangun kembali bangunan yang rentan",
                    "Tidak membuang sampah sembarangan ",
                    "Membangun kembali bangunan yang rentan",
                    "Melakukan pemadatan tanah di sekitar perumahan",
                    "Berlindung di bawah meja"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang harus dihindari untuk mengurangi potensi terjadinya tanah longsor adalah",
                    "Mendirikan bangunan permanen di daerah tebing",
                    "Membuat bangunan dekat pantai",
                    "Mengemudikan mobil",
                    "Mendirikan bangunan permanen di daerah tebing",
                    "Melakukan pemadatan tanah di daerah banjir"
                )
            )
            listData.add(
                gameplayquiz(
                    "Hal yang dapat terjadi jika terjadi hujan pascabencana tanah longsor adalah",
                    "Terjadi tanah longsor susulan",
                    "Tanah berkurang",
                    "Terdapat angin yang berbahaya",
                    "Air pantai surutr",
                    "Terjadi tanah longsor susulan"
                )
            )
            listData.add(
                gameplayquiz(
                    "Pondasi yang disarankan untuk menghindari bahaya liquefaction disebut",
                    "Pondasi tiang pancang",
                    "Pondasi tiang pancang",
                    "Pondasi tapak",
                    "Pondasi dinding",
                    "Pondasi pelat"
                )
            )
            listData.add(
                gameplayquiz(
                    "Dampak negatif dari tanah longsor adalah",
                    "Menimbulkan kerusakan",
                    "Tanaman menjadi subur",
                    "Menimbulkan kerusakan",
                    "Memudahkan dalam pengukuran suhu ruangan",
                    "Membentuk lapisan yang lebih baik"
                )
            )
            listData.add(
                gameplayquiz(
                    "Salah satu faktor penyebab bencana tanah longsor adalah",
                    "Mengetahui saluran dan jalur yang sering dilalui air banjir dan apa dampaknya untuk rumah kita",
                    "Tanah yang kurang padat atau tebal",
                    "Erupsi",
                    "Tanah yang kurang padat atau tebal",
                    "Sungai yang meluap"
                )
            )
            listData.add(
                gameplayquiz(
                    "Contoh tanaman yang dapat membantu mengurangi potensi bencana tanah longsor adalah",
                    "Pohon Nangka",
                    "Pohon kelapa",
                    "Pohon Nangka",
                    "Aglaonema",
                    "Tanaman lidah buaya"
                )
            )
            listData.add(
                gameplayquiz(
                    "Wilayah yang longsor harus dihindari pascabencana tanah longsor dikarenakan",
                    "Kondisi tanah yang labil",
                    "Kondisi tanah kering",
                    "Kondisi tanah yang labil",
                    "Kondisi tanah subur",
                    "Terdapat curah hujan tinggi"
                )
            )

            timer.start()
            nextq()
            nomorsoal.text = "1/10"

            layoutjawabana.setOnClickListener {
                if (trueanswer == "A") {
                    currentscore = 50 + currentscore
                    layoutjawabana.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabana.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabana.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanb.setOnClickListener {
                if (trueanswer == "B") {
                    currentscore = 50 + currentscore
                    layoutjawabanb.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanb.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanb.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawabanc.setOnClickListener {
                if (trueanswer == "C") {
                    currentscore = 50 + currentscore
                    layoutjawabanc.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawabanc.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawabanc.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }
            layoutjawaband.setOnClickListener {
                if (trueanswer == "D") {
                    currentscore = 50 + currentscore
                    layoutjawaband.setBackgroundResource(R.drawable.right_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                } else {
                    layoutjawaband.setBackgroundResource(R.drawable.false_answer_box)
                    Handler().postDelayed({
                        layoutjawaband.setBackgroundResource(R.drawable.edittextround)
                    }, 500)
                    nextq()
                    nomorsoal.text = questionnumber.toString() + "/10"
                }
            }

        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    timer.cancel()
                    val intent = Intent (requireActivity(), HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            })
    }


    fun nextq() {
        val quiznumber = listData.size - 1
        val randomnumber = (0..quiznumber).random()
        soal.text = listData[randomnumber].soal
        jawabana.text = listData[randomnumber].optiona
        jawabanb.text = listData[randomnumber].optionb
        jawabanc.text = listData[randomnumber].optionc
        jawaband.text = listData[randomnumber].optiond

        if (listData[randomnumber].rightanswer == listData[randomnumber].optiona) {
            trueanswer = "A"
        } else if (listData[randomnumber].rightanswer == listData[randomnumber].optionb) {
            trueanswer = "B"
        } else if (listData[randomnumber].rightanswer == listData[randomnumber].optionc) {
            trueanswer = "C"
        } else if (listData[randomnumber].rightanswer == listData[randomnumber].optiond) {
            trueanswer = "D"
        }

        questionnumber++
        listData.removeAt(randomnumber)

        if (questionnumber > 10){
            if(levellist.contains("0")){
                listLevel.add("0")
            }
            if(levellist.contains("1")){
                listLevel.add("1")
            }
            if(levellist.contains("2")){
                listLevel.add("2")
            }
            if(levellist.contains("3")){
                listLevel.add("3")
            }
            if(levellist.contains("4")){
                listLevel.add("4")
            }
            if(levellist.contains("5")){
                listLevel.add("5")
            }


            if(quiztype == 1){
                if (currentscore == 500 && readmodule.contains("1")){
                    if (!levellist.contains(quiztype.toString())) {
                        listLevel.add(quiztype.toString())
                    }
                database.collection("Users").document(mAuth.uid.toString()).update("score", currentscore + jumlahscore,"gold", currentscore + totalgold, "updatescore",
                    currentscore, "level", listLevel, "level1score", currentscore)
                    if (!achieve.contains("1")){
                        listAchieve.add(1)
                        database.collection("Users").document(mAuth.uid.toString()).update("achievementList", listAchieve)
                        Toast.makeText(requireContext(), "Unlock New Achievement", Toast.LENGTH_SHORT).show()
                    }
                }else{
                database.collection("Users").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }
            if(quiztype == 2){
                if (currentscore == 500 && readmodule.contains("2")){
                    if (!levellist.contains(quiztype.toString())) {
                        listLevel.add(quiztype.toString())
                    }
                    database.collection("Users").document(mAuth.uid.toString()).update("score", currentscore + jumlahscore,"gold", currentscore + totalgold, "updatescore",
                        currentscore, "level", listLevel, "level2score", currentscore)
                    if (!achieve.contains("2")){
                        listAchieve.add(2)
                        database.collection("Users").document(mAuth.uid.toString()).update("achievementList", listAchieve)
                        Toast.makeText(requireContext(), "Unlock New Achievement", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    database.collection("Users").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }
            if(quiztype == 3){
                if (currentscore == 500 && readmodule.contains("3")){
                    if (!levellist.contains(quiztype.toString())) {
                        listLevel.add(quiztype.toString())
                    }
                    database.collection("Users").document(mAuth.uid.toString()).update("score", currentscore + jumlahscore,"gold", currentscore + totalgold, "updatescore",
                        currentscore, "level", listLevel, "level3score", currentscore)
                    if (!achieve.contains("3")){
                        listAchieve.add(3)
                        database.collection("Users").document(mAuth.uid.toString()).update("achievementList", listAchieve)
                        Toast.makeText(requireContext(), "Unlock New Achievement", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    database.collection("Users").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }
            if(quiztype == 4){
                if (currentscore == 500 && readmodule.contains("4")){
                    if (!levellist.contains(quiztype.toString())) {
                        listLevel.add(quiztype.toString())
                    }
                    database.collection("Users").document(mAuth.uid.toString()).update("score", currentscore + jumlahscore,"gold", currentscore + totalgold, "updatescore",
                        currentscore, "level", listLevel, "level4score", currentscore)
                    if (!achieve.contains("4")){
                        listAchieve.add(4)
                        database.collection("Users").document(mAuth.uid.toString()).update("achievementList", listAchieve)
                        Toast.makeText(requireContext(), "Unlock New Achievement", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    database.collection("Users").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }
            if(quiztype == 5){
                if (currentscore == 500){
                    database.collection("Users").document(mAuth.uid.toString()).update("score", currentscore + jumlahscore,"gold", currentscore + totalgold, "updatescore",
                        currentscore, "level", listLevel, "level5score", currentscore)
                    if (!achieve.contains("5")){
                        listAchieve.add(5)
                        database.collection("Users").document(mAuth.uid.toString()).update("achievementList", listAchieve)
                        Toast.makeText(requireContext(), "Unlock New Achievement", Toast.LENGTH_SHORT).show()
                    }
                    if (!achieve.contains("7")){
                        listAchieve.add(7)
                        database.collection("Users").document(mAuth.uid.toString()).update("achievementList", listAchieve)
                        Toast.makeText(requireContext(), "Unlock New Achievement", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    database.collection("Users").document(mAuth.uid.toString()).update("updatescore",currentscore, "level",listLevel)
                }
            }
            jawabana.isClickable = false
            jawabanb.isClickable = false
            jawabanc.isClickable = false
            jawaband.isClickable = false

            timer.cancel()

            Handler().postDelayed({
            navController.navigate(R.id.action_gameFragment_to_postgameFragment)
            }, 500)
        }
    }
}
