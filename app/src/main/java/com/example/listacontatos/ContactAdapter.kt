package com.example.listacontatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//Adapter gerencia TODA a lista
class ContactAdapter(var listener: ClickItemContactListener) : RecyclerView.Adapter<ContactAdapter.ContactAdapterViewHolder>() {

    private val list: MutableList<Contact> = mutableListOf()

    //responsavel por criar cada item visual na tela (criação de uma view para a lista)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        //quem é o arq XML(layout) responsável por desenhar cada item na tela (contact_item)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAdapterViewHolder(view, list, listener)
    }

    //obtem o valor de cada item do array e mostra na tela (popular o item na lista)
    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {  //a classe adapter pede pela qtd de itens da lista
        return list.size    //retorna o tamanho da lista sem precisar definir um size fixo
    }

    //Passar uma lista de uma classe externa para o Adapter
    fun updateList(list:List<Contact>) {
        this.list.clear()       //limpa a lista antes de popular
        this.list.addAll(list)  //popula a lista
        notifyDataSetChanged()  //notifica o Adapter dizendo que a lista q o adapter utiliza para fazer a renderização foi
                                // modificada, assim o Adapter fica preparado para popular novamente a lista.
    }


    //responsável por gerenciar CADA item da lista
    class ContactAdapterViewHolder(itemView: View, var list: List<Contact>, var listener: ClickItemContactListener) : RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_phone)
        private val tvPhotograph: ImageView = itemView.findViewById(R.id.iv_photograph)

        init {              //qdo o user tocar no contato, irá ocorrer este evento(abrir detalhes contato)
            itemView.setOnClickListener {
                listener.clickItemContact(list[adapterPosition])
            }
        }

        fun bind(contact: Contact) {    //responsável por popular a lista com os dados
            tvName.text = contact.name
            tvPhone.text = contact.phone

        }
    }
}