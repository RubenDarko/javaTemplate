package com.etaluma.scopesmart.util;

public class DatabaseQueries {

    public static final String SELECT_FROM_PATIENT_TEST_SAMPLE = "select * from patient_well_sample";
    public static final String SELECT_FROM_TEST_BATCH = "select * from test_batch";
    public static final String SELECT_FROM_TEST_BATCH_AUD = "select * from test_batch_aud";
    public static final String SELECT_FROM_CONTROL_SAMPLE = "select * from control_sample";
    public static final String SELECT_FROM_SLIDE = "select * from slide";
    public static final String SELECT_FROM_SLIDE_AUD = "select * from slide_aud";
    public static final String SELECT_FROM_WELL_SAMPLE = "select * from well";
    public static final String SELECT_FROM_WELL_SAMPLE_AUD = "select * from well_sample_aud";
    public static final String CLEAN_DATABASE = "DELETE from well WHERE slide_id=5552";
    /*public static final String CLEAN_DATABASE = "TRUNCATE TABLE assay_configuration,assay_configuration_patterns,assay_configuration_well_locations,control_sample," +
            "control_sample_suggested_patterns," +
            //"image,image_aud,image_classification,image_classification_mitotic_regions," + "image_classification_suggested_patterns,imager," +
            "kit_lot,lis_configuration,lis_configuration_lis_properties,messaging_configuration,messaging_configuration_messaging_properties," +
            "mitotic_region,patient_result,patient_result_aud,patient_sample_suggested_patterns,patient_test_sample,patient_well_sample,pattern," +
            "slide,slide_aud,slide_type,suggested_pattern,system_configuration,system_configuration_system_properties,test_batch," +
            "test_batch_aud,well,well_location,well_sample_aud,well_type CASCADE";*/
    public static final String ADD_USER = "INSERT INTO public.jhi_authority (\"name\") VALUES \n" +
            "('ROLE_USER')\n" +
            ",('ROLE_SUPER_USER')\n" +
            ";\n" +
            "INSERT INTO public.jhi_user (id,created_by,created_date,last_modified_by,last_modified_date,activated,email,first_name,image_url,lang_key,last_name,login) VALUES \n" +
            "('c4af4e2f-b432-4c3b-8405-cca86cd5b97b','user','2019-03-22 15:21:25.056','user','2019-03-22 15:21:25.056',true,'user@localhost','John',NULL,NULL,'Doe','user')\n" +
            ",('c4af4e2f-b432-4c3b-8405-cca86123sa11','test_user_password_expired','2019-04-08 02:12:35.026','test_user_password_expired','2019-04-08 02:12:35.026',true,'test_user_password_expired@localhost','Test',NULL,NULL,'User 1','test_user_password_expired')\n" +
            ";\n" +
            "INSERT INTO public.jhi_user_authority (user_id,authority_name) VALUES \n" +
            "('c4af4e2f-b432-4c3b-8405-cca86cd5b97b','ROLE_USER')\n" +
            ",('c4af4e2f-b432-4c3b-8405-cca86cd5b97b','ROLE_SUPER_USER')\n" +
            ",('c4af4e2f-b432-4c3b-8405-cca86123sa11','ROLE_USER')\n" +
            ";\n" +
            "INSERT INTO public.user_settings (id,password_expiration_date,password_expired,user_id) VALUES \n" +
            "(52,'2019-12-20 15:21:36.251',false,'c4af4e2f-b432-4c3b-8405-cca86cd5b97b')\n" +
            ",(53,'2019-04-07 03:13:21.083',true,'c4af4e2f-b432-4c3b-8405-cca86123sa11')\n" +
            ";";
}
