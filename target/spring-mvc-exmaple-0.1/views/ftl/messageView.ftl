<div id="content">
    <table class="Message">
        <tr>
            <th>id</th>
            <th>text</th>
            <th>chatId
            <th>authorId</th>
        </tr>
    <#list model["messageModel"] as message>
        <tr>
            <td>${message.id}</td>
            <td>${message.text}</td>
            <td>${message.chatId}</td>
            <td>${message.authorId}</td>
        </tr>
    </#list>
    </table>
</div>