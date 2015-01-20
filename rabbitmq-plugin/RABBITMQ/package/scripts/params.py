from resource_management.libraries.functions.version import format_hdp_stack_version, compare_versions
from resource_management import *

config = Script.get_config()

rabbitmq_server_bin = '/etc/init.d/rabbitmq-server'
rabbitmq_plugins_bin = '/usr/lib/rabbitmq/bin/rabbitmq-plugins'
rabbitmq_server_pid_file = '/var/run/rabbitmq/pid'
rabbitmq_server_user = 'rabbitmq'
rabbitmq_server_home = '/var/lib/rabbitmq'

smokeuser = config['configurations']['cluster-env']['smokeuser']
