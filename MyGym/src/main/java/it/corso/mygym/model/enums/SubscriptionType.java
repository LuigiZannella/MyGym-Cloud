package it.corso.mygym.model.enums;

public enum SubscriptionType {

        SALA_PESI("SALA_PESI"),
        OPEN("OPEN");

        private String type;

        SubscriptionType(String type){
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

