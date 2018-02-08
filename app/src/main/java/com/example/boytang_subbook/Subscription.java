/**
 *  Subscription
 *
 *  Version 1.0
 *
 *  Feb 08, 2018
 *
 *  Copyright © 2018 Diane B, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of Code of Student Behavior at
 *  University of Alberta.
 *  You can find a copy of this license in this project. Otherwise, please contact boytang@ualberta.ca
 */
package com.example.boytang_subbook;

import java.util.Date;

/**
 * Represents a subscription
 *
 * @author Diane B
 *
 * @version 1.0
 */

    public class Subscription {

        //Attributes
        private String name;
        private String date;
        private float amount;
        private String comment;


        //Constructors

    /**
     * Creates a Subscription
     * @param name name of Subscription
     * @param date date Subscription was started
     * @param amount monthly cost of subscription
     */
        public Subscription(String name, String date, float amount){ //no comment added by user
            this.name = name;
            this.date = date;
            this.amount = amount;
        }

    /**
     * Creates a Subscription
     * @param name name of Subscription
     * @param date date Subscription was started
     * @param amount monthly cost of subscription
     * @param comment user comment about subscription
     */
        public Subscription(String name, String date, float amount, String comment){ //comment added by user
            this.name = name;
            this.date = date;
            this.amount = amount;
            this.comment = comment;
        }


        //Getters

    /**
     * Gets the name of the subscription
     * @return name of subscription
     */
      public String getName() {
            return name;
        }

    /**
     * Gets start date of subscription
     * @return date subscription was started
     */

    public String getDate() {
            return date;
        }

    /**
     * Gets monthly cost of subscription
     * @return monthly cost of subscription
     */

    public float getAmount() {
            return amount;
        }

    /**
     * Gets user comment on subscription
     * @return user comment
     */


    public String getComment() {
            return comment;
        }


        //Setters


    /**
     * Sets the name of the subscription
     * @param name name of the subscription
     * @throws NameTooLongException is thrown when the name is over 20 chars
     * @see NameTooLongException
     */

        public void setName(String name) throws NameTooLongException {
            if (name.length() > 20){
                throw new NameTooLongException();
            }
            else{
                this.name = name;
            }
        }

    /**
     * Sets the monthly cost of the subscription
     * @param amount monthly cost of the subscription
     * @throws AmountNegativeException is thrown when the amount is less than $0.00
     * @see AmountNegativeException
     */

    public void setAmount(float amount) throws AmountNegativeException {
            if (amount < 0){
                throw new AmountNegativeException();
            }
            else {
                this.amount = amount;
            }
        }

    /**
     * Sets the date the subscription was started
     * @param date the date the subscription was started
     */

    public void setDate(String date) {
            this.date = date;
        }


    /**
     * Sets the comment about the subscription
     * @param comment the comment about the subscription
     * @throws CommentTooLongException is thrown when the comment is over 30 chars
     * @see CommentTooLongException
     */

    public void setComment(String comment) throws CommentTooLongException {
            if (comment.length() > 30) {
                throw new CommentTooLongException();
            }
            else if (comment.length() == 0){
                this.comment = "No comment";
            }
            else {
                this.comment = comment;

            }
        }

    }
