import jenkins.model.Jenkins
import java.util.logging.Level
import java.util.logging.Logger

final def LOG = Logger.getLogger("LABS")

def checkmarxBaseUrl = System.getenv('CHECKMARX_SERVER_URL')

if(checkmarxBaseUrl != null) {
  LOG.log(Level.INFO,  'Configuring checkmarx...' )

  def checkmarxCredentialId = System.getenv('CHECKMARX_CREDENTIAL_ID')

  def checkmarx = Jenkins.instance.getDescriptorByType(com.checkmarx.jenkins.CxScanBuilder.DescriptorImpl)

  checkmarx.serverUrl = checkmarxBaseUrl
  checkmarx.credentialsId = checkmarxCredentialId ? System.getenv('OPENSHIFT_BUILD_NAMESPACE') + "-" + checkmarxCredentialId : ''
  checkmarx.save()

  LOG.log(Level.INFO,  'Configured checkmarx' )
}
