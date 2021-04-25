package com.example.pojo;

import java.util.Date;

public class Data {
    private final String customerNo;
    private final int activated;
    private final int emailOutput;
    private final int phoneOutput;
    private final int faxOutput;
    private final String dateCreated;
    private final String dateModified;

    private Data(DataBuilder builder) {
        this.customerNo = builder.customerNo;
        this.activated = builder.activated;
        this.emailOutput = builder.emailOutput;
        this.phoneOutput = builder.phoneOutput;
        this.faxOutput = builder.faxOutput;
        this.dateCreated = builder.dateCreated;
        this.dateModified = builder.dateModified;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public int getActivated() {
        return activated;
    }

    public int getEmailOutput() {
        return emailOutput;
    }

    public int getPhoneOutput() {
        return phoneOutput;
    }

    public int getFaxOutput() {
        return faxOutput;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public int getCustomerNoWithoutPrefix(){
        return Integer.parseInt(this.customerNo.substring(1));
    }

    public Data changeModifiedDate(String dateModified) {
        return new DataBuilder(this.customerNo)
                .activated(activated)
                .faxOutput(faxOutput)
                .emailOutput(emailOutput)
                .phoneOutput(phoneOutput)
                .dateCreated(dateCreated)
                .dateModified(dateModified).build();
    }

    public Data changeCreatedDateToUTC(String dateCreated) {
        return new DataBuilder(this.customerNo)
                .activated(activated)
                .faxOutput(faxOutput)
                .emailOutput(emailOutput)
                .phoneOutput(phoneOutput)
                .dateModified(dateModified)
                .dateCreated(dateCreated)
                .build();
    }

    @Override
    public String toString() {
        return String.format(
                "%-15s %-15s %-23s %-23s %-18s %-35s %-15s",customerNo,activated, emailOutput, phoneOutput, faxOutput, dateCreated, dateModified);
    }

    public static class DataBuilder {
        private final String customerNo;
        private  int activated;
        private int emailOutput;
        private  int phoneOutput;
        private  int faxOutput;
        private String dateCreated;
        private String dateModified;


        public DataBuilder(String customerNo) {
            this.customerNo = customerNo;
        }

        public DataBuilder activated(int activated) {
            this.activated = activated;
            return this;
        }

        public DataBuilder emailOutput(int emailOutput){
            this.emailOutput = emailOutput;
            return this;
        }

        public DataBuilder phoneOutput(int phoneOutput) {
            this.phoneOutput = phoneOutput;
            return this;
        }

        public DataBuilder faxOutput(int faxOutput) {
            this.faxOutput = faxOutput;
            return this;
        }

        public DataBuilder dateModified(String dateModified) {
            this.dateModified = dateModified;
            return this;
        }

        public DataBuilder dateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public Data build() {
            Data data = new Data(this);
            return data;
        }
    }
}
