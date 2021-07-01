package com.kpu.todolist_realm
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter
import android.text.format.DateFormat

class TodoListAdapter(realmResult:OrderedRealmCollection<Todo>) :RealmBaseAdapter<Todo>(realmResult){
    class ViewHolder(view:View){
        val dateTextVIew:TextView=view.findViewById(R.id.text1)
        val textTextView:TextView=view.findViewById(R.id.textClean)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh:ViewHolder
        val view:View

        if(convertView==null){
            view=LayoutInflater.from(parent?.context).inflate(R.layout.item_todo,parent,false)
            vh=ViewHolder(view)
            view.tag=vh
        }
        else{
            view=convertView
            vh=view.tag as ViewHolder
        }

        if(adapterData!=null){
            val item=adapterData!![position]
            vh.textTextView.text=item.title
            vh.dateTextVIew.text=DateFormat.format("MM/dd",item.date)

        }
        return view
    }

    override fun getItemId(position: Int): Long {
        if(adapterData!=null){
            return adapterData!![position].id
        }
        return super.getItemId(position)
    }
}