package com.riseinsteps.androidlearnings.roomdb;

import android.app.Application;
import android.os.AsyncTask;

import com.riseinsteps.androidlearnings.model.Model;

public class RoomRepository {
    private ModelDatabase modelDatabase;
    private RoomDao roomDao;
    private Model model;

    public RoomRepository(Application application) {
        modelDatabase = ModelDatabase.getInstance(application.getApplicationContext());
        roomDao = modelDatabase.roomDao();
        model = roomDao.getModel();
    }

    public void insert(Model model) {
        new InsertAsyncTask(roomDao).execute(model);
    }

    public Model getModel() {
        return model;
    }

    private class InsertAsyncTask extends AsyncTask<Model, Void, Void> {
        private RoomDao roomDao;

        public InsertAsyncTask(RoomDao roomDao) {
            this.roomDao = roomDao;
        }

        @Override
        protected Void doInBackground(Model... models) {
            roomDao.insert(models[0]);
            return null;
        }
    }
}
