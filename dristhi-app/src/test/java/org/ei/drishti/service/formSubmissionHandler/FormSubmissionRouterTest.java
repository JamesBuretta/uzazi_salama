package org.ei.drishti.service.formSubmissionHandler;

import com.xtremelabs.robolectric.RobolectricTestRunner;
import org.ei.drishti.domain.form.FormSubmission;
import org.ei.drishti.repository.FormDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.ei.drishti.util.FormSubmissionBuilder.create;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class FormSubmissionRouterTest {
    @Mock
    private FormDataRepository formDataRepository;
    @Mock
    private ECRegistrationHandler ecRegistrationHandler;
    @Mock
    private FPComplicationsHandler fpComplicationsHandler;

    private FormSubmissionRouter router;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        router = new FormSubmissionRouter(formDataRepository, ecRegistrationHandler, fpComplicationsHandler);
    }

    @Test
    public void shouldDelegateFormSubmissionHandlingToHandlerBasedOnFormName() throws Exception {
        FormSubmission formSubmission = create().withFormName("ec_registration").withInstanceId("instance id 1").withVersion("122").build();
        when(formDataRepository.fetchFromSubmission("instance id 1")).thenReturn(formSubmission);

        router.route("instance id 1");

        verify(formDataRepository).fetchFromSubmission("instance id 1");
        verify(ecRegistrationHandler).handle(formSubmission);

        formSubmission = create().withFormName("fp_complications").withInstanceId("instance id 2").withVersion("123").build();
        when(formDataRepository.fetchFromSubmission("instance id 2")).thenReturn(formSubmission);

        router.route("instance id 2");

        verify(formDataRepository).fetchFromSubmission("instance id 2");
        verify(fpComplicationsHandler).handle(formSubmission);
    }
}