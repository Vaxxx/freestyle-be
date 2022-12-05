package ng.com.createsoftware.freestylebe.service;

import ng.com.createsoftware.freestylebe.model.SecModel;

public interface SecModelService {

    SecModel getSecModelByUser(Long userId);

    //edit SecModel
    SecModel editSecModel(SecModel secModel, Long userId) ;
}
