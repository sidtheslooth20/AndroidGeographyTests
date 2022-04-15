package com.example.androidgeographytests.Fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.androidgeographytests.R
import kotlinx.android.synthetic.main.fragment_home2.*
import kotlinx.android.synthetic.main.fragment_home2.view.*
import java.io.File
import com.richpath.RichPath
import com.richpath.RichPathView
import kotlinx.android.synthetic.main.fragment_home2.view.*
import java.util.stream.IntStream.range


class HomeFragment : Fragment() {

    val KEY_NUMBERS = "KEY_NUMBERS"
    val KEY_NUMBER = "KEY_NUMBER"

    var mScaleFactor = 1.0f

    var numbers: ArrayList<DataToPassThrough> = arrayListOf()
    var number = 0

    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            mScaleFactor *= detector!!.scaleFactor

            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f))

            view?.invalidate()
            return true
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        for (i in range(1, 82))
            numbers.add(
                i - 1,
                DataToPassThrough(i)
            )    //77-ЕАО 78-Крым 79-НАО 80-ХМАО 81-ЧАО 82-ЯНАО
        numbers.shuffle()
        var total_mistake_count = 0
        var mistake_count = 0
        number = numbers.removeAt(0).code

        val subjectsMap = mapOf<Int, String>(
            1 to "республику Адыгея",
            2 to "республику Башкортостан",
            3 to "республику Бурятия",
            4 to "республику Алтай",
            5 to "республику Дагестан",
            6 to "республику Ингушетия",
            7 to "Кабардино-Балкарскую республику",
            8 to "республику Калмыкия",
            9 to "Карачаево-Черкесскую республику",
            10 to "республику Карелия",
            11 to "республику Коми",
            12 to "республику Марий-Эл",
            13 to "республику Мордовия",
            14 to "республику Саха-Якутия",
            15 to "республику Северная Осетия-Алания",
            16 to "республику Татарстан",
            17 to "республику Тува",
            18 to "Удмуртскую республику",
            19 to "республику Хакасия",
            20 to "Чеченскую республику",
            21 to "Чувашскую республику",
            22 to "Алтайский край",
            23 to "Краснодарский край",
            24 to "Красноярский край",
            25 to "Приморский край",
            26 to "Ставропольский край",
            27 to "Хабаровский край",
            28 to "Амурскую область",
            29 to "Архангельскую область",
            30 to "Астраханскую область",
            31 to "Белгородскую область",
            32 to "Брянскую область",
            33 to "Владимирскую область",
            34 to "Волгоградскую область",
            35 to "Вологодскую область",
            36 to "Воронежскую область",
            37 to "Ивановскую область",
            38 to "Иркутскую область",
            39 to "Калининградскую область",
            40 to "Калужскую область",
            41 to "Камчатский край",
            42 to "Кемеровскую область",
            43 to "Кировскую область",
            44 to "Костромскую область",
            45 to "Курганскую область",
            46 to "Курскую область",
            47 to "Ленинградскую область",
            48 to "Липецкую область",
            49 to "Магаданскую область",
            50 to "Московскую область",
            51 to "Мурманскую область",
            52 to "Нижегородскую область",
            53 to "Новгородскую область",
            54 to "Новосибирскую область",
            55 to "Омскую область",
            56 to "Оренбургскую область",
            57 to "Орловскую область",
            58 to "Пензенскую область",
            59 to "Пермский край",
            60 to "Псковскую область",
            61 to "Ростовскую область",
            62 to "Рязанскую область",
            63 to "Самарскую область",
            64 to "Саратовскую область",
            65 to "Сахалинскую область",
            66 to "Свердловскую область",
            67 to "Смоленскую область",
            68 to "Тамбовскую область",
            69 to "Тверскую область",
            70 to "Томскую область",
            71 to "Тульскую область",
            72 to "Тюменскую область",
            73 to "Ульяновскую область",
            74 to "Челябинскую область",
            75 to "Забайкальский край",
            76 to "Ярославскую область",
            77 to "Еврейскую АО",
            78 to "республику Крым",
            79 to "Ненецкий АО",
            80 to "Ханты-Мансийский АО",
            81 to "Чукотский АО",
            82 to "Ямало-Ненецкий АО"
        )


        val view = inflater.inflate(R.layout.fragment_home2, container, false)

        view.textView.text = "Найдите " + subjectsMap[number]

        if (savedInstanceState != null) {
            numbers = savedInstanceState.getParcelableArrayList<DataToPassThrough>(KEY_NUMBERS)!!
            number = savedInstanceState.getParcelable<DataToPassThrough>(KEY_NUMBER)!!.code
            view.textView.text = "Найдите " + subjectsMap[number]
        }

        view.rpView.setOnPathClickListener {

            if (mistake_count >= 4)
                rpView.findRichPathByName(number.toString())?.fillColor = Color.CYAN
            if (number.toString() == it.name) {
                it.fillColor = Color.GREEN
                mistake_count = 0
                if (numbers.isNotEmpty() && mistake_count == 0) {
                    number = numbers.removeAt(0).code
                    view.textView.text = "Найдите " + subjectsMap[number]
                }
            } else {
                it.fillColor = Color.RED
                mistake_count += 1
                total_mistake_count += 1
            }
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(KEY_NUMBERS, numbers)
        outState.putParcelable(KEY_NUMBER, DataToPassThrough(number))

        super.onSaveInstanceState(outState)
    }
}