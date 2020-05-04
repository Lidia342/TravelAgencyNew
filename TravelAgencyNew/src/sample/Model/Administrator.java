package sample.Model;



    public class Administrator extends Person {

        private int administratorId;
        public Administrator(String SSN, String firstName, String lastName, String phoneNumber, String password, String address, String email, int administratorId) {
            super(SSN, firstName, lastName, phoneNumber, password, address, email);
            this.administratorId = administratorId;
        }

        public int getAdministratorId() {
            return administratorId;
        }

        public void setAdministratorId(int administratorId) {
            this.administratorId = administratorId;
        }
    }

