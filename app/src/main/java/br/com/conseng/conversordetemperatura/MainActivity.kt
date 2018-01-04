package br.com.conseng.conversordetemperatura

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTipoConversao.setOnCheckedChangeListener { _, _ -> alterouTipoConversao() }
        btnConverter.setOnClickListener { converter() }
    }

    enum class temperaturaOrigem { CELSIUS, FAHRENHEIT }

    fun entrada(): temperaturaOrigem {
        val temp = if (btnTipoConversao.isChecked) temperaturaOrigem.CELSIUS else temperaturaOrigem.FAHRENHEIT
        return temp
    }

    fun ajustaOrigem() {
        val unidade = if (temperaturaOrigem.CELSIUS == entrada()) "C" else "F"
        txtEscala.text = unidade
    }

    fun alterouTipoConversao() {
        ajustaOrigem()
        converter()
    }

    fun formatoConvertido(): String {
        val formato = "= %.2f " + if (temperaturaOrigem.CELSIUS == entrada()) "F" else "C"
        return formato
    }

    fun toFahrenheit(celsius: Float): Float {
        val fahrenheit = (celsius * 9 / 5) + 32
        return fahrenheit
    }

    fun toCelsius(fahrenheit: Float): Float {
        val celsius = (fahrenheit - 32) * 5 / 9
        return celsius
    }

    fun converter() {
        try {
            val origem = txtTemperatura.text.toString().toFloat()
            val temp = if (temperaturaOrigem.CELSIUS == entrada()) toFahrenheit(origem) else toCelsius(origem)
            val formato = formatoConvertido()
            txtTemperaturaConvertida.text = formato.format(temp)
        } catch (e: NumberFormatException) {
            txtTemperaturaConvertida.text = getString(R.string.parametro_invalido)
        }
    }
}
