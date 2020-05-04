package sample.Model;


    public class Customer extends Person {


        private int customerId;


        public Customer(String SSN, String firstName, String lastName, String phoneNumber, String password, String address, String email, int customerId) {
            super(SSN, firstName, lastName, phoneNumber, password, address, email);
            this.customerId = customerId;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;

        }
    }

