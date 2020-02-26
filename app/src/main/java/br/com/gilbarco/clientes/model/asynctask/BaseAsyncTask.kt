package br.com.gilbarco.clientes.model.asynctask

import android.os.AsyncTask

class BaseAsyncTask<T>(
    private val whenExecuted: () -> T,
    private val whenEnded: (result: T) -> Unit
) : AsyncTask<Void, Void, T>() {

    override fun doInBackground(vararg params: Void?) = whenExecuted()

    override fun onPostExecute(result: T) {
        super.onPostExecute(result)
        whenEnded(result)
    }

}