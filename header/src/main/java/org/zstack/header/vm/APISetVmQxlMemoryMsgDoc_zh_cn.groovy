package org.zstack.header.vm

import org.zstack.header.vm.APISetVmQxlMemoryEvent

doc {
    title "指定云主机的显存(SetVmQxlMemory)"

    category "vmInstance"

    desc """设置云主机的显存大小，当云主机的显卡类型为qxl时生效"""

    rest {
        request {
			url "PUT /v1/vm-instances/{uuid}/actions"

			header (Authorization: 'OAuth the-session-uuid')

            clz APISetVmQxlMemoryMsg.class

            desc """"""
            
			params {

				column {
					name "uuid"
					enclosedIn "setVmQxlMemory"
					desc "云主机UUID"
					location "url"
					type "String"
					optional false
					since "0.6"
					
				}
				column {
					name "ram"
					enclosedIn "setVmQxlMemory"
					desc ""
					location "body"
					type "Integer"
					optional true
					since "0.6"
					
				}
				column {
					name "vram"
					enclosedIn "setVmQxlMemory"
					desc ""
					location "body"
					type "Integer"
					optional true
					since "0.6"
					
				}
				column {
					name "vgamem"
					enclosedIn "setVmQxlMemory"
					desc ""
					location "body"
					type "Integer"
					optional true
					since "0.6"
					
				}
				column {
					name "systemTags"
					enclosedIn ""
					desc ""
					location "body"
					type "List"
					optional true
					since "0.6"
					
				}
				column {
					name "userTags"
					enclosedIn ""
					desc ""
					location "body"
					type "List"
					optional true
					since "0.6"
					
				}
			}
        }

        response {
            clz APISetVmQxlMemoryEvent.class
        }
    }
}