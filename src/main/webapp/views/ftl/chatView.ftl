<div id="content">
    <table class="Chat">
        <tr>
            <th>id</th>
            <th>creatorId</th>
            <th>name</th>
        </tr>
    <#list model["chatModel"] as chat>
        <tr>
            <td>${chat.id}</td>
            <td>${chat.creatorId}</td>
            <td>${user.name}</td>
        </tr>
    </#list>
    </table>
</div>