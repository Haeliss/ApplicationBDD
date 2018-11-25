package com.example.f5onz.applicationbdd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), AnkoLogger {

    val courseDb by lazy { CourseDb(CourseDbHelper(applicationContext)) }
    var list = listOf<MobileCourse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showList()

        btnAjouter.setOnClickListener{
            doAsync {
                val course1 = MobileCourse(1,txtNomCours.text.toString(), txtNbHeures.text.toString().toInt())
                courseDb.saveCourse(course1)

            }
            showList()
            toast("Enregistrement ajouté")
            txtNomCours.setText("", TextView.BufferType.EDITABLE)
            txtNbHeures.setText("",TextView.BufferType.EDITABLE)
            showList()
        }
        btnSupprimer.setOnClickListener(){
            doAsync{
                courseDb.deleteCourse(txtIDDelete.text.toString().toInt())
            }
            showList()
            toast("Enregistrement supprimé")
            txtIDDelete.setText("",TextView.BufferType.EDITABLE)
            showList()
        }

        btnModifier.setOnClickListener{
            doAsync {
                courseDb.modifyCourse(txtID.text.toString().toInt(),txtNouveauNom.text.toString(),txtNouveauDuree.text.toString().toInt())
            }
            showList()
            toast("Enregistrement modifié")
            txtID.setText("",TextView.BufferType.EDITABLE)
            txtNouveauNom.setText("",TextView.BufferType.EDITABLE)
            txtNouveauDuree.setText("",TextView.BufferType.EDITABLE)
            showList()
        }



    }
    private fun showList() {
        list = courseDb.requestCourse()
        listeCours.text = ""
        for (c in list)
            listeCours.text=listeCours.text.toString()+"ID: ${c.id} Nom du cours : ${c.title} Durée : ${c.time}H\n"
    }
}
