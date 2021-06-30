mpackage com.kpu.calculatormenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId)
        {
            R.id.action_plus->{
                if(editNum1.text.toString()==""||editNum2.text.toString()==""){
                    toast("숫자를 입력하세요!")
                    return false

                }
                saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())
                val resultVal=editNum1.text.toString().toInt()+editNum2.text.toString().toInt()
                result.setText("계산 결과 : "+resultVal.toString())
                return true
            }

            R.id.action_minus->{

                if(editNum1.text.toString()==""||editNum2.text.toString()==""){
                    toast("숫자를 입력하세요!")
                    return false

                }

                saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())
                val resultVal=editNum1.text.toString().toInt()-editNum2.text.toString().toInt()
                result.setText("계산 결과 : "+resultVal.toString())
                return true
            }

            R.id.action_mul->{

                if(editNum1.text.toString()==""||editNum2.text.toString()==""){
                    toast("숫자를 입력하세요!")
                    return false

                }

                saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())
                val resultVal=editNum1.text.toString().toInt()*editNum2.text.toString().toInt()
                result.setText("계산 결과 : "+resultVal.toString())
                return true
            }

            R.id.action_div->{
                if(editNum1.text.toString()==""||editNum2.text.toString()==""){
                    toast("숫자를 입력하세요!")
                    return false

                }
                if(editNum2.text.toString().toInt()==0){
                    toast("0으로 나눌 수 없습니다!")
                    return false

                }
                saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())
                val resultVal=editNum1.text.toString().toFloat()/editNum2.text.toString().toFloat()
                result.setText("계산 결과 : "+resultVal.toString())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.delete_num1->{
                deleteNum1()
                return true
            }
            R.id.delete_num2->{
                deleteNum2()
                return true
            }
            R.id.delete_all->{
                deleteAll()
                result.setText("계산 결과 : ")
                return true
            }
        }
        return super.onContextItemSelected(item)
    }



    private fun saveData(num1:Int,num2:Int){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()

        editor.putInt("KEY_NUM1",num1).apply()
        editor.putInt("KEY_NUM2",num2).apply()
    }

    private fun loadData(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        var num1=pref.getInt("KEY_NUM1",0)
        var num2=pref.getInt("KEY_NUM2",0)

        if(num1!=0&&num2!=0){
            editNum1.setText(num1.toString())
            editNum2.setText(num2.toString())
        }
    }

    private fun deleteNum1(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()
        editor.remove("KEY_NUM1")
        editNum1.setText(null)
    }

    private fun deleteNum2(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()
        editor.remove("KEY_NUM2")
        editNum2.setText(null)
    }

    private fun deleteAll(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()
        editor.clear()
        editNum1.setText(null)
        editNum2.setText(null)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="calculatorMenu"

        registerForContextMenu(result)

//        btnNum1.setOnClickListener {
//            deleteNum1()
//        }
//
//        btnNum2.setOnClickListener {
//            deleteNum2()
//        }
//
//        btnAll.setOnClickListener {
//            deleteAll()
//        }

        loadData()
//        plusButton.setOnClickListener {
//
//            if(editNum1.text.toString()==""||editNum2.text.toString()==""){
//                toast("숫자를 입력하세요!")
//                return@setOnClickListener
//
//            }
//
//            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())
//            val resultVal=editNum1.text.toString().toInt()+editNum2.text.toString().toInt()
//            result.setText(resultVal.toString())
//
//
//        }
//        minusButton.setOnClickListener {
//
//            if(editNum1.text.toString()==""||editNum2.text.toString()==""){
//                toast("숫자를 입력하세요!")
//                return@setOnClickListener
//
//            }
//            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())
//            val resultVal=editNum1.text.toString().toInt()-editNum2.text.toString().toInt()
//            result.setText(resultVal.toString())
//
//
//        }
//        divButton.setOnClickListener {
//            if(editNum1.text.toString()==""||editNum2.text.toString()==""){
//                toast("숫자를 입력하세요!")
//                return@setOnClickListener
//
//            }
//            if(editNum2.text.toString().toInt()==0){
//                toast("0으로 나눌 수 없습니다!")
//                return@setOnClickListener
//
//            }
//            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())
//            val resultVal=editNum1.text.toString().toFloat()/editNum2.text.toString().toFloat()
//            result.setText(resultVal.toString())
//
//        }
//        mulButton.setOnClickListener {
//
//            if(editNum1.text.toString()==""||editNum2.text.toString()==""){
//                toast("숫자를 입력하세요!")
//                return@setOnClickListener
//
//            }
//            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())
//            val resultVal=editNum1.text.toString().toInt()*editNum2.text.toString().toInt()
//            result.setText(resultVal.toString())
//
//
//        }
    }
}